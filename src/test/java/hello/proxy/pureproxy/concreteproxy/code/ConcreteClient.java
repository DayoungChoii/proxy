package hello.proxy.pureproxy.concreteproxy.code;

public class ConcreteClient {

    public ConcreteLogic concreteLogic;

    public ConcreteClient(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public String execute() {
        return concreteLogic.operate();
    }
}
