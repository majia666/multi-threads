package com.majia.designpattern.context;

public class QueryFromHttpAction {

    public void execute(){
        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    public String getCardId(String name){
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "74397192739" + Thread.currentThread().getId();
    }
}
