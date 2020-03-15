package com.majia.designpattern.context;

public final class ActionContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private static class ContextHodler {
        private static final ActionContext actionContext = new ActionContext();
    }

    public static ActionContext getActionContext() {
        return ContextHodler.actionContext;
    }

    public Context getContext() {
        return threadLocal.get();
    }
}
