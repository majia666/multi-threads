package com.majia.designpattern.context;

public class QueryFromDBAction {

    public void execute(){
        try{
            Thread.sleep(1000);
            String name = "majia " + Thread.currentThread().getName();
            Context context = ActionContext.getActionContext().getContext();
            context.setName(name);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
