package com.jss.secrets;

import co.com.bancolombia.secretsmanager.api.GenericManager;
import co.com.bancolombia.secretsmanager.connector.AWSSecretManagerConnector;
import co.com.bancolombia.secretsmanager.model.AWSSecretDBModel;

public class SearchSecret {

    private static String REGION_SECRET = "us-east-1";
    //private static String NAME_SECRET = "aw0491001-peq-open-dev-rds-secretrds-cnxpeq";
    //private static String NAME_SECRET = "nu2030001-api-portal-dev-dpunfdcd-secretrds-cnx1";
    //private static String NAME_SECRET = "aw0818001-cte-traceddb-secretrds-usrAdmin";
    private static String NAME_SECRET = "rds_secret_credentials";

    public void getAndPrintSecret(){
        GenericManager connector = new AWSSecretManagerConnector(REGION_SECRET);
        SimpleModel secret = null;
        try {
            secret = connector.getSecret(NAME_SECRET, SimpleModel.class);
        }catch (Exception e){
            System.out.println("Exception getting the aws secret: " + e.getMessage());
        }

        if(secret != null){
            System.out.println("The aws secret is: " + secret.getPassword());
        }else{
            System.out.println("Not find the secret with the name: " + NAME_SECRET);
        }

    }

}
