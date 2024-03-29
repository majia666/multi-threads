package com.majia.secondpart.chapter10;

public class BrokerDelegateClassLoader extends MyClassLoader {

    public BrokerDelegateClassLoader() {
    }

    public BrokerDelegateClassLoader(String classDir) {
        super(classDir);
    }

    public BrokerDelegateClassLoader(String classDir, ClassLoader parent) {
        super(classDir, parent);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)){
            Class<?> klass = findLoadedClass(name);
            if (klass == null){
                if (name.startsWith("java.") || name.startsWith("javax.") ){
                    try{
                        klass = getSystemClassLoader().loadClass(name);
                    }catch (Exception e){

                    }

                }else {
                    try{
                        klass = this.findClass(name);
                    }catch (Exception e){

                    }
                    if (klass == null){
                        if (getParent() != null) {
                            klass = getParent().loadClass(name);
                        }else {
                            klass = getSystemClassLoader().loadClass(name);
                        }
                    }
                }

            }
            if(null == klass){
                throw new ClassNotFoundException("The class " + name + " not find.");
            }
            if (resolve){
                resolveClass(klass);
            }
            return klass;
        }

    }
}
