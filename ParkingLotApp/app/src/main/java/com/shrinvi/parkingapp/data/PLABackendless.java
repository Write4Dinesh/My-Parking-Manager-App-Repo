package com.shrinvi.parkingapp.data;

import android.util.Log;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.shrinvi.parkingapp.data.network.PLAResponse;
import com.shrinvi.parkingapp.model.ParkingSpace;
import com.shrinvi.parkingapp.ui.PLAHomeActivity;
import com.shrinvi.parkingapp.utility.DBConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PLABackendless {
    private static PLABackendless sPLABackendless;

    private PLABackendless() {

    }

    public static PLABackendless getInstance() {
        if (sPLABackendless == null) {
            sPLABackendless = new PLABackendless();
        }
        return sPLABackendless;
    }

    public void createAllParkingSpaces(List<ParkingSpace> parkingSpaceList, OnResponseListener listener) {
        ArrayList mFreeSpaces = new ArrayList<>(parkingSpaceList.size());
        Map mapOfSpaces;
        for (ParkingSpace space : parkingSpaceList) {
            mapOfSpaces = new HashMap();
            mapOfSpaces.put(DBConstants.PARKING_SPACE_TABLE_COLUMN_ID, space.getId());
            mapOfSpaces.put(DBConstants.PARKING_SPACE_TABLE_COLUMN_IS_EMPTY, space.getIsEmpty());
            mFreeSpaces.add(mapOfSpaces);
        }
        IDataStore<Map> parkingSpaceTable = Backendless.Data.of(DBConstants.PARKING_SPACE_TABLE_NAME);
        parkingSpaceTable.create(mFreeSpaces, new AsyncCallback<List<String>>() {
            @Override
            public void handleResponse(List<String> response) {
                if (listener != null) {
                    PLAResponse<List<String>> dataResponse = new PLAResponse<>();
                    dataResponse.response = response;
                    dataResponse.success = true;
                    listener.onResponse(dataResponse);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                PLAResponse<List<String>> dataResponse = new PLAResponse<>();
                dataResponse.success = false;
                dataResponse.error = fault.getMessage();
                listener.onResponse(dataResponse);
            }
        });
    }

    public void getAllSpacesFromServer(OnResponseListener listener) {
        IDataStore<Map> parkingSpaceTable = Backendless.Data.of(DBConstants.PARKING_SPACE_TABLE_NAME);
        DataQueryBuilder dataQuery = DataQueryBuilder.create();
        List<String> sortBy = new ArrayList<String>();
        sortBy.add(DBConstants.PARKING_SPACE_TABLE_COLUMN_ID);
        //queryOptions.
        // set query options into data query
        dataQuery.setSortBy(sortBy);
        dataQuery.setPageSize(PLAHomeActivity.PARKING_CAPACITY);

        parkingSpaceTable.find(dataQuery, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> response) {
                ArrayList<ParkingSpace> spaces = null;
                if (response != null && response.size() > 0) {
                    spaces = new ArrayList<>(response.size());
                    ParkingSpace space;
                    for (Map map : response) {
                        Object object = map.get(DBConstants.PARKING_SPACE_TABLE_COLUMN_ID);
                        if (object != null) {
                            String id = object.toString();
                            space = new ParkingSpace(Integer.parseInt(id));
                            space.setIsEmpty(Boolean.getBoolean(map.get(DBConstants.PARKING_SPACE_TABLE_COLUMN_IS_EMPTY).toString()));
                            spaces.add(space);
                        }
                    }
                }
                PLAResponse<List<ParkingSpace>> datResponse = new PLAResponse<>();
                datResponse.response = spaces;
                datResponse.success = true;
                listener.onResponse(datResponse);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                PLAResponse<String> datResponse = new PLAResponse<>();
                datResponse.response = fault.getMessage();
                datResponse.success = false;
                listener.onResponse(datResponse);
            }
        });
    }

    public void updateParkingSpace(ParkingSpace parkingSpace, OnResponseListener listener) {
        IDataStore<Map> parkingTable = Backendless.Data.of(DBConstants.PARKING_SPACE_TABLE_NAME);
        parkingTable.update("id=" + parkingSpace.getId(), getMap(parkingSpace), new AsyncCallback<Integer>() {
            @Override
            public void handleResponse(Integer NoOfObjectsUpdated) {
                if (listener != null) {
                    PLAResponse<Integer> dataResponse = new PLAResponse<>();
                    dataResponse.success = true;
                    dataResponse.response = NoOfObjectsUpdated;
                    listener.onResponse(dataResponse);
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                PLAResponse<Integer> dataResponse = new PLAResponse<>();
                dataResponse.success = false;
                dataResponse.error = fault.getMessage();
                listener.onResponse(dataResponse);
            }
        });
    }

    private Map getMap(ParkingSpace space) {
        Map tempSpace = new HashMap();
        tempSpace.put("id", space.getId());
        tempSpace.put("is_empty", space.getIsEmpty());
        return tempSpace;
    }

    private void subscribeForObjectUpdate(Map response, IDataStore<Map> testTableDataStore) {
        testTableDataStore.rt().addUpdateListener("objectId='" + response.get("objectId") + "'", new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                //changeSavedValue(response);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                //PLAHomeActivity.this.handleFault(fault);
            }
        });
    }

    private void handleFault(BackendlessFault fault) {
        String msg = "Server reported an error " + fault.getMessage();
        Log.e("MYAPP", msg);
        /*status.setText(msg);
        updateButton.setEnabled(true);*/
    }


    private ArrayList<OnResponseListener> mListeners = new ArrayList<>();

    public void registerResponseListener(OnResponseListener listener) {
        mListeners.add(listener);
    }

    private void publishResponse(PLAResponse response) {
        for (OnResponseListener listener : mListeners) {
            listener.onResponse(response);
            mListeners.remove(listener);
        }
    }

    public interface OnResponseListener {
        void onResponse(PLAResponse response);
    }
}
