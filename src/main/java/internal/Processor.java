package internal;

import kernel.model.App;

public class Processor {
    private Class<?> myClass;
    private App app = new App();

    private Processor(Class<?> myClass){
        this.myClass = myClass;
    }

    private void process() {
        //TODO
    }

    String getResult(){
        return ""; //TODO
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String myClass = "internal.demo.MonApp";

        Processor processor = new Processor(Class.forName(myClass));
        processor.process();
        System.out.println(processor.getResult());
    }
}
