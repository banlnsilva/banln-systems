package com.banln.hwkim.common;

public class Constants {
    public enum ExceptionClass {
        PRODUCT("Product"), ORDER("Order"), PROVIDER("Provider"), SIGN("Sign");
        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return getExceptionClass() + " Exception.";
        }
    }
}
