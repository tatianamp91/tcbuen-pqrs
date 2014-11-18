package com.tcbuen.pqrs.mail;

public interface IMail {
    
    public void send(String destino,String asunto, String mensaje);
}
