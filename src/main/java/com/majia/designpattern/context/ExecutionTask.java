package com.majia.designpattern.context;

public class ExecutionTask implements Runnable {

    private QueryFromDBAction dbAction = new QueryFromDBAction();

    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {

        dbAction.execute();
        System.out.println("The name query is successful.");
        httpAction.execute();
        System.out.println("The card id query is successful.");
        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The name is " + context.getName() + " and card id " + context.getCardId());
    }
}
