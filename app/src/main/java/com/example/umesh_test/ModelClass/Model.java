
package com.example.umesh_test.ModelClass;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Model {

    private Integer id;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("PostOffice")
    @Expose
    private List<PostOffice> postOffice = new ArrayList<>();

    public Model(Integer id, String message, String status, List<PostOffice> postOffice) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.postOffice = postOffice;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PostOffice> getPostOffice() {
        return postOffice;
    }

   public void setPostOffice(List<PostOffice> postOffice) {
        this.postOffice = postOffice;
    }

}
