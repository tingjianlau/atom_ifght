package me.ifight.atom_ifght.model;

public class RestResult {
    public RestResult(){

    }

    private int total;
    private Object data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
