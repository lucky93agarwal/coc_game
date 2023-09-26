package com.challengers.of.call;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.net.SocketTimeoutException;

public class WebService {
    private static String NAMESPACE = "urn:cocws";

//    private static String URL = "http://coc.sigmasoftwares.org/LiveServices.asmx";
    private static String URL = "http://callofchallengers.com/coc/api/webservice-server.php";


    private static String SOAP_ACTION = "urn:cocws#";

public static String ReturnMoney(String Loginid,String Contestid, String webMethName) {
    String resTxt = null;
    // Create request
    SoapObject request = new SoapObject(NAMESPACE, webMethName);

    // Property which holds input parameters
    PropertyInfo celsiusPI = new PropertyInfo();
    // Set Name
    celsiusPI.setName("Loginid");
    celsiusPI.setValue(Loginid);
    celsiusPI.setType(String.class);
    request.addProperty(celsiusPI);

    PropertyInfo celsiusPI4 = new PropertyInfo();
    // Set Name
    celsiusPI4.setName("Contestid");
    celsiusPI4.setValue(Contestid);
    celsiusPI4.setType(String.class);
    request.addProperty(celsiusPI4);
    // Create envelope
    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
            SoapEnvelope.VER11);
    envelope.dotNet = true;
    // Set output SOAP object
    envelope.setOutputSoapObject(request);
    // Create HTTP call object
    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
    try {
        MarshalDouble md = new MarshalDouble();
        md.register(envelope);
        androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
        // Get the response
        Object response =  envelope.getResponse();
        resTxt = response.toString();
    } catch (SocketTimeoutException e) {
        e.printStackTrace();
        resTxt = "Unable to connect to server";
    } catch (Exception e) {
        e.printStackTrace();
        resTxt = "connection fault";
    }
    return resTxt;
}
    public static String Login(String Userid, String Password, String Module, String Status, String Errorresponse, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters


        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Userid");
        celsiusPI.setValue(Userid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);
        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Password");
        celsiusPI1.setValue(Password);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);



        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Module");
        celsiusPI2.setValue(Module);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);



        PropertyInfo celsiusPI3 = new PropertyInfo();
        celsiusPI3.setName("Status");
        celsiusPI3.setValue(Status);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);



        PropertyInfo celsiusPI4 = new PropertyInfo();
        celsiusPI4.setName("Errorresponse");
        celsiusPI4.setValue(Errorresponse);
        // Set dataType
        celsiusPI4.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI4);


        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();


            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Type(String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);



        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }



    public static String Selectchallengethis(String Loginid, String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String FeedBack(String Email, String Name, String Message,String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters


        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Email");
        celsiusPI.setValue(Email);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Name");
        celsiusPI1.setValue(Name);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);



        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Message");
        celsiusPI2.setValue(Message);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);




        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();


            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////



    public static String CheckUpdate(String version,String application,String imei,String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters


        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("version");
        // Set Value
        celsiusPI.setValue(version);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("application");
        // Set Value
        celsiusPI1.setValue(application);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("imei");
        // Set Value
        celsiusPI2.setValue(imei);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);


        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();


            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }
    public static String Getappversion(String Version, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Version");
        // Set Value
        celsiusPI.setValue(Version);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Logins(String Username, String Password, String Role,String IMEINumber, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Username");
        celsiusPI.setValue(Username);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Password");
        celsiusPI1.setValue(Password);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Role");
        celsiusPI2.setValue(Role);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        celsiusPI3.setName("imei");
        celsiusPI3.setValue(IMEINumber);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
//        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
//            System.out.print(resTxt);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }
    public static String Paytm(String TXNID, String BANKTXNID, String ORDERID, String TXNAMOUNT, String STATUS, String TXNTYPE, String GATEWAYNAME, String RESPCODE,String RESPMSG, String BANKNAME, String MID, String PAYMENTMODE, String REFUNDAMT, String TXNDATE,String custID, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("TXNID");
        celsiusPI.setValue(TXNID);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("BANKTXNID");
        celsiusPI2.setValue(BANKTXNID);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("ORDERID");
        celsiusPI3.setValue(ORDERID);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("TXNAMOUNT");
        celsiusPI4.setValue(TXNAMOUNT);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);

        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("STATUS");
        celsiusPI5.setValue(STATUS);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        PropertyInfo celsiusPI6 = new PropertyInfo();
        // Set Name
        celsiusPI6.setName("TXNTYPE");
        celsiusPI6.setValue(TXNTYPE);
        celsiusPI6.setType(String.class);
        request.addProperty(celsiusPI6);

        PropertyInfo celsiusPI7 = new PropertyInfo();
        // Set Name
        celsiusPI7.setName("GATEWAYNAME");
        celsiusPI7.setValue(GATEWAYNAME);
        celsiusPI7.setType(String.class);
        request.addProperty(celsiusPI7);

        PropertyInfo celsiusPI8 = new PropertyInfo();
        // Set Name
        celsiusPI8.setName("RESPCODE");
        celsiusPI8.setValue(RESPCODE);
        celsiusPI8.setType(String.class);
        request.addProperty(celsiusPI8);

        PropertyInfo celsiusPI9 = new PropertyInfo();
        // Set Name
        celsiusPI9.setName("RESPMSG");
        celsiusPI9.setValue(RESPMSG);
        celsiusPI9.setType(String.class);
        request.addProperty(celsiusPI9);

        PropertyInfo celsiusPI10 = new PropertyInfo();
        // Set Name
        celsiusPI10.setName("BANKNAME");
        celsiusPI10.setValue(BANKNAME);
        celsiusPI10.setType(String.class);
        request.addProperty(celsiusPI10);

        PropertyInfo celsiusPI11 = new PropertyInfo();
        // Set Name
        celsiusPI11.setName("MID");
        celsiusPI11.setValue(MID);
        celsiusPI11.setType(String.class);
        request.addProperty(celsiusPI11);

        PropertyInfo celsiusPI12 = new PropertyInfo();
        // Set Name
        celsiusPI12.setName("PAYMENTMODE");
        celsiusPI12.setValue(PAYMENTMODE);
        celsiusPI12.setType(String.class);
        request.addProperty(celsiusPI12);

        PropertyInfo celsiusPI13 = new PropertyInfo();
        // Set Name
        celsiusPI13.setName("REFUNDAMT");
        celsiusPI13.setValue(REFUNDAMT);
        celsiusPI13.setType(String.class);
        request.addProperty(celsiusPI13);


        PropertyInfo celsiusPI14 = new PropertyInfo();
        // Set Name
        celsiusPI14.setName("TXNDATE");
        celsiusPI14.setValue(TXNDATE);
        celsiusPI14.setType(String.class);
        request.addProperty(celsiusPI14);

        PropertyInfo celsiusPI15 = new PropertyInfo();
        // Set Name
        celsiusPI15.setName("custID");
        celsiusPI15.setValue(custID);
        celsiusPI15.setType(String.class);
        request.addProperty(celsiusPI15);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
//        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
//            System.out.print(resTxt);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Getotp(String name, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("MobileNo");
        // Set Value
        celsiusPI.setValue(name);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object

        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);


            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }









    public static String BannedApp(String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object

        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);


            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }









    public static String Getsponsorid(String Sponsorid,String Username,String Mobile, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Sponsorid");
        // Set Value
        celsiusPI.setValue(Sponsorid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("Username");
        // Set Value
        celsiusPI1.setValue(Username);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Mobile");
        // Set Value
        celsiusPI2.setValue(Mobile);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);






        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
//        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Withdraw(String Mobile, String  amount, String Loginid,String OrderId, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Mobile");
        // Set Value
        celsiusPI.setValue(Mobile);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);


        // Property which holds input parameters
        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("Amount");
        // Set Value
        celsiusPI1.setValue(amount);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        // Property which holds input parameters
        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Loginid");
        // Set Value
        celsiusPI2.setValue(Loginid);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("OrderId");
        // Set Value
        celsiusPI3.setValue(OrderId);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);


        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
//        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {

            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }










    public static String Getotp1(String name,String Action, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("MobileNo");
        // Set Value
        celsiusPI.setValue(name);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Action");
        // Set Value
        celsiusPI2.setValue(Action);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
//        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Confirmotp(String Mobile, String Msg, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("MobileNo");
        celsiusPI.setValue(Mobile);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);
        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Msg");
        celsiusPI1.setValue(Msg);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Updateprofilepic(String Loginid, String Imageurl,String Imagebytes,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);



        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Imageurl");
        celsiusPI1.setValue(Imageurl);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Imagebytes");
        celsiusPI2.setValue(Imagebytes);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);


        PropertyInfo celsiusPI3 = new PropertyInfo();
        celsiusPI3.setName("Count");
        celsiusPI3.setValue(Count);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }



    public static String Like(String Username, String Flag, int Payout, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Username");
        celsiusPI.setValue(Username);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Flag");
        celsiusPI2.setValue(Flag);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);


        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Payout");
        celsiusPI3.setValue(Payout);
        celsiusPI3.setType(int.class);
        request.addProperty(celsiusPI3);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Socialregistration(String Name, String Mobile, String Email, String Password, String Address, String Gender, String Dob, String Mentionby, String SponserID, String Role,String Action,String IMEINumber, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters


        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Name");
        celsiusPI1.setValue(Name);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Mobile");
        celsiusPI2.setValue(Mobile);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        celsiusPI3.setName("Email");
        celsiusPI3.setValue(Email);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);


        PropertyInfo celsiusPI4 = new PropertyInfo();
        celsiusPI4.setName("Password");
        celsiusPI4.setValue(Password);
        // Set dataType
        celsiusPI4.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI4);

        PropertyInfo celsiusPI5 = new PropertyInfo();
        celsiusPI5.setName("Address");
        celsiusPI5.setValue(Address);
        // Set dataType
        celsiusPI5.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI5);

        PropertyInfo celsiusPI6 = new PropertyInfo();
        celsiusPI6.setName("Gender");
        celsiusPI6.setValue(Gender);
        // Set dataType
        celsiusPI6.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI6);

        PropertyInfo celsiusPI7 = new PropertyInfo();
        celsiusPI7.setName("Dob");
        celsiusPI7.setValue(Dob);
        // Set dataType
        celsiusPI7.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI7);

        PropertyInfo celsiusPI8 = new PropertyInfo();
        celsiusPI8.setName("Mentionby");
        celsiusPI8.setValue(Mentionby);
        // Set dataType
        celsiusPI8.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI8);

        PropertyInfo celsiusPI9 = new PropertyInfo();
        celsiusPI9.setName("SponserID");
        celsiusPI9.setValue(SponserID);
        // Set dataType
        celsiusPI9.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI9);



        PropertyInfo celsiusPI11 = new PropertyInfo();
        celsiusPI11.setName("Role");
        celsiusPI11.setValue(Role);
        // Set dataType
        celsiusPI11.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI11);


        PropertyInfo celsiusPI12 = new PropertyInfo();
        celsiusPI12.setName("Action");
        celsiusPI12.setValue(Action);
        // Set dataType
        celsiusPI12.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI12);



        PropertyInfo celsiusPI13 = new PropertyInfo();
        celsiusPI13.setName("imei");
        celsiusPI13.setValue(IMEINumber);
        // Set dataType
        celsiusPI13.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI13);


        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String otp( String SocialPassword, String SocialAction, String SocialEmail, String SocialRole, String SocialGender,String Username,String UserMobile,String UserSponsorid,String ImageURL, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters


//        PropertyInfo celsiusPI1 = new PropertyInfo();
//        celsiusPI1.setName("SocialName");
//        celsiusPI1.setValue(SocialName);
//        // Set dataType
//        celsiusPI1.setType(String.class);
//        // Add the property to request object
//        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("SocialPassword");
        celsiusPI2.setValue(SocialPassword);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);


        PropertyInfo celsiusPI4 = new PropertyInfo();
        celsiusPI4.setName("SocialAction");
        celsiusPI4.setValue(SocialAction);
        // Set dataType
        celsiusPI4.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI4);






        PropertyInfo celsiusPI10 = new PropertyInfo();
        celsiusPI10.setName("SocialEmail");
        celsiusPI10.setValue(SocialEmail);
        // Set dataType
        celsiusPI10.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI10);

        PropertyInfo celsiusPI11 = new PropertyInfo();
        celsiusPI11.setName("SocialRole");
        celsiusPI11.setValue(SocialRole);
        // Set dataType
        celsiusPI11.setType(String.class);
        // Add the property to request object   Userid
        request.addProperty(celsiusPI11);


        PropertyInfo celsiusPI12 = new PropertyInfo();
        celsiusPI12.setName("SocialGender");
        celsiusPI12.setValue(SocialGender);
        // Set dataType
        celsiusPI12.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI12);

//        PropertyInfo celsiusPI13 = new PropertyInfo();
//        celsiusPI13.setName("Userid");
//        celsiusPI13.setValue(Userid);
//        // Set dataType
//        celsiusPI13.setType(String.class);
//        // Add the property to request object
//        request.addProperty(celsiusPI13);

        PropertyInfo celsiusPI14 = new PropertyInfo();
        celsiusPI14.setName("Username");
        celsiusPI14.setValue(Username);
        // Set dataType
        celsiusPI14.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI14);

        PropertyInfo celsiusPI15 = new PropertyInfo();
        celsiusPI15.setName("UserMobile");
        celsiusPI15.setValue(UserMobile);
        // Set dataType
        celsiusPI15.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI15);

        PropertyInfo celsiusPI16 = new PropertyInfo();
        celsiusPI16.setName("UserSponsorid");
        celsiusPI16.setValue(UserSponsorid);
        // Set dataType
        celsiusPI16.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI16);

        PropertyInfo celsiusPI17 = new PropertyInfo();
        celsiusPI17.setName("ImageURL");
        celsiusPI17.setValue(ImageURL);
        // Set dataType
        celsiusPI17.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI17);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }




    public static String Userregistration(String Username, String mobile, String Password, String Sponsorid, String Role, String Action, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters


        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Name");
        celsiusPI1.setValue(Username);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Mobile");
        celsiusPI2.setValue(mobile);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);


        PropertyInfo celsiusPI4 = new PropertyInfo();
        celsiusPI4.setName("Password");
        celsiusPI4.setValue(Password);
        // Set dataType
        celsiusPI4.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI4);






        PropertyInfo celsiusPI10 = new PropertyInfo();
        celsiusPI10.setName("SponsorID");
        celsiusPI10.setValue(Sponsorid);
        // Set dataType
        celsiusPI10.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI10);

        PropertyInfo celsiusPI11 = new PropertyInfo();
        celsiusPI11.setName("Role");
        celsiusPI11.setValue(Role);
        // Set dataType
        celsiusPI11.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI11);


        PropertyInfo celsiusPI12 = new PropertyInfo();
        celsiusPI12.setName("Action");
        celsiusPI12.setValue(Action);
        // Set dataType
        celsiusPI12.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI12);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Createcontest(String Loginid, String Nickname, String Entryfees, String Winningamount, String Gameid,double Amount, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Nickname");
        celsiusPI2.setValue(Nickname);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Entryfees");
        celsiusPI3.setValue(Entryfees);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Winningamount");
        celsiusPI4.setValue(Winningamount);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);

        PropertyInfo celsiusPI6 = new PropertyInfo();
        // Set Name
        celsiusPI6.setName("Gameid");
        celsiusPI6.setValue(Gameid);
        celsiusPI6.setType(String.class);
        request.addProperty(celsiusPI6);
        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Amount");
        celsiusPI5.setValue(Amount);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }



    public static String Refundamount(String Loginid, String Contestid, String Entryfees, String Winningamount,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Contestid");
        celsiusPI2.setValue(Contestid);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Entryfees");
        celsiusPI3.setValue(Entryfees);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Winningamount");
        celsiusPI4.setValue(Winningamount);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);
        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Count");
        celsiusPI5.setValue(Count);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Declareresult(String Loginid, String Contestid, String Msg,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Contestid");
        celsiusPI2.setValue(Contestid);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Msg");
        celsiusPI3.setValue(Msg);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);
        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Count");
        celsiusPI5.setValue(Count);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }



    public static String Changepassword(String Loginid, String Newpassword, String Oldpassword, String Usertype,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Newpassword");
        celsiusPI2.setValue(Newpassword);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Oldpassword");
        celsiusPI3.setValue(Oldpassword);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Usertype");
        celsiusPI4.setValue(Usertype);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);
        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Count");
        celsiusPI5.setValue(Count);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Updateprofile(String LoginId, String DOB, String Gender, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("LoginId");
        celsiusPI.setValue(LoginId);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);



        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("DOB");
        celsiusPI3.setValue(DOB);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Gender");
        celsiusPI4.setValue(Gender);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);


        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {

            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Changeresultstatus(String Loginid,String Contestid,String Winningamount, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Contestid");
        celsiusPI4.setValue(Contestid);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);

        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Winningamount");
        celsiusPI5.setValue(Winningamount);
        celsiusPI5.setType(double.class);
        request.addProperty(celsiusPI5);


        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Createchallenge(String Loginid, String Nickname,String Contestid,String Newcontestid, String Entryfees,String Amount,String Gameid, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Nickname");
        celsiusPI2.setValue(Nickname);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Contestid");
        celsiusPI4.setValue(Contestid);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);

        PropertyInfo celsiusPI6 = new PropertyInfo();
        // Set Name
        celsiusPI6.setName("Newcontestid");
        celsiusPI6.setValue(Newcontestid);
        celsiusPI6.setType(String.class);
        request.addProperty(celsiusPI6);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Entryfees");
        celsiusPI3.setValue(Entryfees);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);


        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Amount");
        celsiusPI5.setValue(Amount);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        PropertyInfo celsiusPI8 = new PropertyInfo();
        // Set Name
        celsiusPI8.setName("Gameid");
        celsiusPI8.setValue(Gameid);
        celsiusPI8.setType(String.class);
        request.addProperty(celsiusPI8);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Joincontestchallenger(String Loginid,String Contestid,String Gameid,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Contestid");
        celsiusPI4.setValue(Contestid);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);

        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Gameid");
        celsiusPI5.setValue(Gameid);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        PropertyInfo celsiusPI6 = new PropertyInfo();
        // Set Name
        celsiusPI6.setName("Count");
        celsiusPI6.setValue(Count);
        celsiusPI6.setType(String.class);
        request.addProperty(celsiusPI6);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Countcontest(String Loginid,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Count");
        celsiusPI5.setValue(Count);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }



    public static String Checkmobile(String Mobile,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Mobile");
        celsiusPI.setValue(Mobile);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Count");
        celsiusPI5.setValue(Count);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }














    public static String Checkexistence(String Nickname,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Nickname");
        celsiusPI.setValue(Nickname);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Count");
        celsiusPI5.setValue(Count);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String CheckAmount(String amount,String Loginid, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Amount");
        celsiusPI.setValue(amount);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("Loginid");
        celsiusPI1.setValue(Loginid);
        celsiusPI1.setType(String.class);
        request.addProperty(celsiusPI1);



        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {

            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Selectlife(String Loginid, String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);




        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }



    public static String Selectchallenge(String Loginid,String GameName, String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Gameid");
        celsiusPI2.setValue(GameName);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }
    public static String Selectbtn(String Loginid,String object, String  webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        celsiusPI.setValue(Loginid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);
        PropertyInfo celsiusP2 = new PropertyInfo();
        // Set Name
        celsiusP2.setName("object");
        celsiusP2.setValue(object);
        celsiusP2.setType(String.class);
        request.addProperty(celsiusP2);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Selectquestion(String  webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }



    public static String Selectresult(String Loginid,String GameName, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Gameid");
        // Set Value
        celsiusPI2.setValue(GameName);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);




        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Selectmycontest(String Loginid, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);





        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Insertanswer(String Loginid,String Contestid,String Questionid,String Rightanswer,String Answer,String Point,String Counter,String Size,String TotalLifes, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("Contestid");
        // Set Value
        celsiusPI1.setValue(Contestid);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);


        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Questionid");
        // Set Value
        celsiusPI2.setValue(Questionid);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Rightanswer");
        // Set Value
        celsiusPI3.setValue(Rightanswer);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Answer");
        // Set Value
        celsiusPI4.setValue(Answer);
        // Set dataType
        celsiusPI4.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI4);

        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Point");
        // Set Value
        celsiusPI5.setValue(Point);
        // Set dataType
        celsiusPI5.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI5);

        PropertyInfo celsiusPI6 = new PropertyInfo();
        // Set Name
        celsiusPI6.setName("Counter");
        // Set Value
        celsiusPI6.setValue(Counter);
        // Set dataType
        celsiusPI6.setType(int.class);
        // Add the property to request object
        request.addProperty(celsiusPI6);

        PropertyInfo celsiusPI7 = new PropertyInfo();
        // Set Name
        celsiusPI7.setName("Size");
        // Set Value
        celsiusPI7.setValue(Size);
        // Set dataType
        celsiusPI7.setType(int.class);
        // Add the property to request object
        request.addProperty(celsiusPI7);


        PropertyInfo celsiusPI8 = new PropertyInfo();
        // Set Name
        celsiusPI8.setName("TotalLifes");
        // Set Value
        celsiusPI8.setValue(TotalLifes);
        // Set dataType
        celsiusPI8.setType(int.class);
        // Add the property to request object
        request.addProperty(celsiusPI8);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Insertanswer1(String Loginid,String Contestid,String Inseranswer,int Size,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("Contestid");
        // Set Value
        celsiusPI1.setValue(Contestid);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);


        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Inseranswer");
        // Set Value
        celsiusPI2.setValue(Inseranswer);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI7 = new PropertyInfo();
        // Set Name
        celsiusPI7.setName("Size");
        // Set Value
        celsiusPI7.setValue(Size);
        // Set dataType
        celsiusPI7.setType(int.class);
        // Add the property to request object
        request.addProperty(celsiusPI7);

        PropertyInfo celsiusPI8 = new PropertyInfo();
        // Set Name
        celsiusPI8.setName("Count");
        // Set Value
        celsiusPI8.setValue(Count);
        // Set dataType
        celsiusPI8.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI8);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Insertfreeanswer1(String Loginid,String Contestid,String Inseranswer,int Size,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("Contestid");
        // Set Value
        celsiusPI1.setValue(Contestid);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);


        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Inseranswer");
        // Set Value
        celsiusPI2.setValue(Inseranswer);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI7 = new PropertyInfo();
        // Set Name
        celsiusPI7.setName("Size");
        // Set Value
        celsiusPI7.setValue(Size);
        // Set dataType
        celsiusPI7.setType(int.class);
        // Add the property to request object
        request.addProperty(celsiusPI7);

        PropertyInfo celsiusPI8 = new PropertyInfo();
        // Set Name
        celsiusPI8.setName("Count");
        // Set Value
        celsiusPI8.setValue(Count);
        // Set dataType
        celsiusPI8.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI8);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service

            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Selectanswer(String Loginid,String Contestid,String TotalFifty, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("Contestid");
        // Set Value
        celsiusPI1.setValue(Contestid);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("TotalFifty");
        // Set Value
        celsiusPI2.setValue(TotalFifty);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String WalletHistory(String Loginid, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);



        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }




    public static String Selectfreequizdashboard(String Loginid,String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);



        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Createfreequiz(String Loginid,String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("Count");
        // Set Value
        celsiusPI1.setValue(Count);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response = envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Selectleaderboard(String Loginid,String topObject,String from, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters

        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("topObject");
        // Set Value
        celsiusPI1.setValue(topObject);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("from");
        // Set Value
        celsiusPI2.setValue(from);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();

            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }
    public static String Selectleaderboardfree(String Loginid,String from, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters

        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);



        PropertyInfo celsiusPI1 = new PropertyInfo();
        // Set Name
        celsiusPI1.setName("from");
        // Set Value
        celsiusPI1.setValue(from);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();

            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Paytmstart(String Loginid, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters

        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Loginid");
        // Set Value
        celsiusPI.setValue(Loginid);
        // Set dataType
        celsiusPI.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();

            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }






    public static String Manageprofile(String Referalcode, String Name, String Address, String Flag, String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Referalcode");
        celsiusPI1.setValue(Referalcode);
        celsiusPI1.setType(String.class);
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Name");
        celsiusPI2.setValue(Name);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        celsiusPI3.setName("Address");
        celsiusPI3.setValue(Address);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        celsiusPI4.setName("Flag");
        celsiusPI4.setValue(Flag);
        // Set dataType
        celsiusPI4.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI4);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Insertpoint(String Username, String Taskid, String Amount, String Count, String webMethName) {
        String resTxt = null;
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Username");
        celsiusPI1.setValue(Username);
        celsiusPI1.setType(String.class);
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Taskid");
        celsiusPI2.setValue(Taskid);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        celsiusPI3.setName("Amount");
        celsiusPI3.setValue(Amount);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        celsiusPI4.setName("Count");
        celsiusPI4.setValue(Count);
        // Set dataType
        celsiusPI4.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI4);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Forgetpassword(String userid, String Password, String Msg, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Userid");
        celsiusPI1.setValue(userid);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Password");
        celsiusPI2.setValue(Password);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        celsiusPI3.setName("Msg");
        celsiusPI3.setValue(Msg);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Selectad(String Username, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Username");
        celsiusPI1.setValue(Username);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);
        // Property which holds input parameters

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Selectchallengedetail(int Appid, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Appid");
        celsiusPI.setValue(Appid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Displaylevel(String Referalcode, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Referalcode");
        celsiusPI.setValue(Referalcode);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Managenetwrokreport(String Referalcode, String from, String Action, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Referalcode");
        celsiusPI.setValue(Referalcode);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("from");
        celsiusPI2.setValue(from);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);


        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Action");
        celsiusPI3.setValue(Action);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Updatebankdetail(String Referalcode, String Accountno, String Accounttype, String Bankname, String IFSCcode, String Action, String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Referalcode");
        celsiusPI.setValue(Referalcode);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Accountno");
        celsiusPI2.setValue(Accountno);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);


        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Accounttype");
        celsiusPI3.setValue(Accounttype);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Bankname");
        celsiusPI4.setValue(Bankname);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);

        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("IFSCcode");
        celsiusPI5.setValue(IFSCcode);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        PropertyInfo celsiusPI6 = new PropertyInfo();
        // Set Name
        celsiusPI6.setName("Action");
        celsiusPI6.setValue(Action);
        celsiusPI6.setType(String.class);
        request.addProperty(celsiusPI6);

        PropertyInfo celsiusPI7 = new PropertyInfo();
        // Set Name
        celsiusPI7.setName("Count");
        celsiusPI7.setValue(Count);
        celsiusPI7.setType(String.class);
        request.addProperty(celsiusPI7);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Managechallenge(String Challengeid, String Appid, String Userid, String Status, String Action, String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Challengeid");
        celsiusPI.setValue(Challengeid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Appid");
        celsiusPI2.setValue(Appid);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Userid");
        celsiusPI3.setValue(Userid);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Status");
        celsiusPI4.setValue(Status);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);


        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Action");
        celsiusPI5.setValue(Action);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        PropertyInfo celsiusPI6 = new PropertyInfo();
        // Set Name
        celsiusPI6.setName("Count");
        celsiusPI6.setValue(Count);
        celsiusPI6.setType(String.class);
        request.addProperty(celsiusPI6);


        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Managevideo(String ID, String Userid, double Credit, double Debit, String Action, String Type, String Count, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("ID");
        celsiusPI.setValue(ID);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI2 = new PropertyInfo();
        // Set Name
        celsiusPI2.setName("Userid");
        celsiusPI2.setValue(Userid);
        celsiusPI2.setType(String.class);
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        // Set Name
        celsiusPI3.setName("Credit");
        celsiusPI3.setValue(Credit);
        celsiusPI3.setType(String.class);
        request.addProperty(celsiusPI3);

        PropertyInfo celsiusPI4 = new PropertyInfo();
        // Set Name
        celsiusPI4.setName("Debit");
        celsiusPI4.setValue(Debit);
        celsiusPI4.setType(String.class);
        request.addProperty(celsiusPI4);


        PropertyInfo celsiusPI5 = new PropertyInfo();
        // Set Name
        celsiusPI5.setName("Action");
        celsiusPI5.setValue(Action);
        celsiusPI5.setType(String.class);
        request.addProperty(celsiusPI5);

        PropertyInfo celsiusPI6 = new PropertyInfo();
        // Set Name
        celsiusPI6.setName("Type");
        celsiusPI6.setValue(Type);
        celsiusPI6.setType(String.class);
        request.addProperty(celsiusPI6);


        PropertyInfo celsiusPI7 = new PropertyInfo();
        // Set Name
        celsiusPI7.setName("Count");
        celsiusPI7.setValue(Count);
        celsiusPI7.setType(String.class);
        request.addProperty(celsiusPI7);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service

            MarshalDouble md = new MarshalDouble();
            md.register(envelope);
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Selectearnmore(String Type, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Type");
        celsiusPI.setValue(Type);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);


        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Updateprofile(String Agentid, String address, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Agentid");
        celsiusPI.setValue(Agentid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("address");
        celsiusPI1.setValue(address);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Changepassword(String Userid, String Oldpassword, String Password, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Userid");
        celsiusPI.setValue(Userid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("Oldpassword");
        celsiusPI1.setValue(Oldpassword);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        celsiusPI3.setName("Password");
        celsiusPI3.setValue(Password);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Selectuserbookingdetail(String Customerid, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);

        // Property which holds input parameters
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("Customerid");
        celsiusPI.setValue(Customerid);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }

    public static String Sendreferal(String refcode,String Mobile, String Username, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("refcode");
        celsiusPI.setValue(refcode);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);



        PropertyInfo celsiusPI2 = new PropertyInfo();
        celsiusPI2.setName("Mobile");
        celsiusPI2.setValue(Mobile);
        // Set dataType
        celsiusPI2.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI2);

        PropertyInfo celsiusPI3 = new PropertyInfo();
        celsiusPI3.setName("Username");
        celsiusPI3.setValue(Username);
        // Set dataType
        celsiusPI3.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI3);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            Object response =  envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }


    public static String Uploadfile(String fileName, String bytes1, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        PropertyInfo celsiusPI = new PropertyInfo();
        // Set Name
        celsiusPI.setName("fileName");
        celsiusPI.setValue(fileName);
        celsiusPI.setType(String.class);
        request.addProperty(celsiusPI);

        PropertyInfo celsiusPI1 = new PropertyInfo();
        celsiusPI1.setName("bytes1");
        celsiusPI1.setValue(bytes1);
        // Set dataType
        celsiusPI1.setType(String.class);
        // Add the property to request object
        request.addProperty(celsiusPI1);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            // Invole web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            resTxt = response.toString();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            resTxt = "Unable to connect to server";
        } catch (Exception e) {
            e.printStackTrace();
            resTxt = "connection fault";
        }
        return resTxt;
    }
}
