package com.springboot.app.Service;

import com.springboot.app.Entity.Request;
import com.springboot.app.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService implements RequestServiceInterface{

    @Autowired
    private RequestRepository repoReq ;


    @Override
    public List<Request> getAllRequests() {
        return repoReq.findAll();
    }

    @Override
    public void saveRequest(Request request) {
        this.repoReq.save(request);
    }

    @Override
    public Request getRequestById(Long id) {
        return repoReq.getRequestById(id);
    }

    @Override
    public void deleteRequestById(Long id) {
        this.repoReq.deleteById(id);
    }

    public List<Request> getStudentRequests(Long id) {
        List<Request> requestlist =  repoReq.findAll();
        for(int i=requestlist.size()-1; i>=0 ; i--) {
            if(!requestlist.get(i).getSender().getId().equals(id)) {
                requestlist.remove(i);
            }
        }
        return requestlist;
    }

    public List<Request> getTeacherRequests(Long id) {
        List<Request> requestlist =  repoReq.findAll();
        for(int i=requestlist.size()-1; i>=0 ; i--) {
            if(!requestlist.get(i).getReceiver().getId().equals(id)) {
                requestlist.remove(i);
            }
        }
        return requestlist;
    }

}
