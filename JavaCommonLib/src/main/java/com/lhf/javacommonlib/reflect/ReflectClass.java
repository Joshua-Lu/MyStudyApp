package com.lhf.javacommonlib.reflect;

/**
 * 该类用于测试反射相关方法
 * Created by Joshua on 2020/10/26.
 */
class ReflectClass {

    public String publicField;
    protected String protectedField;
    String defaultField;
    private String privateField;

    public ReflectClass() {
        System.out.println("ReflectClass.ReflectClass() called");
    }

    public ReflectClass(String publicField, String protectedField, String defaultField, String privateField) {
        this.publicField = publicField;
        this.protectedField = protectedField;
        this.defaultField = defaultField;
        this.privateField = privateField;
    }

    public String getPublicField() {
        return publicField;
    }

    public void setPublicField(String publicField) {
        this.publicField = publicField;
    }

    public String getProtectedField() {
        return protectedField;
    }

    public void setProtectedField(String protectedField) {
        this.protectedField = protectedField;
    }

    public String getDefaultField() {
        return defaultField;
    }

    public void setDefaultField(String defaultField) {
        this.defaultField = defaultField;
    }

    public String getPrivateField() {
        return privateField;
    }

    public void setPrivateField(String privateField) {
        System.out.println("ReflectClass.setPrivateField() called with: privateField = [" + privateField + "]");
        this.privateField = privateField;
    }

    private void privateMethod() {
        System.out.println("ReflectClass.privateMethod() called");
    }

    @Override
    public String toString() {
        return "ReflectClass{" +
                "publicField='" + publicField + '\'' +
                ", protectedField='" + protectedField + '\'' +
                ", defaultField='" + defaultField + '\'' +
                ", privateField='" + privateField + '\'' +
                '}';
    }
}
