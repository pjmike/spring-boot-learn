package com.pjmike.qiniu;

/**
 * @author pjmike
 * @create 2018-08-20 17:09
 */
public class ServiceA {
    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public ServiceA() {
        serviceB = new ServiceB();
    }

    public void doService() {
        System.out.println("do something..");
        serviceB.doSomething();
    }

    public static void main(String[] args) {
        ServiceA serviceA = new ServiceA();
        serviceA.doService();
    }

    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
