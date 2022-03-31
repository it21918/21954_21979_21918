package com.springboot.app.Service;

import com.springboot.app.Entity.Request;

import java.util.List;

public interface RequestServiceInterface {

    List<Request> getAllRequests();
    void saveRequest(Request request);
    Request getRequestById(Long id);
    void deleteRequestById(Long id);
}
