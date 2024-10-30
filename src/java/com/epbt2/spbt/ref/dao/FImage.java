package com.epbt2.spbt.ref.dao;


import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

 /**
  * Ppfoto Value Object.
  * This class is value object representing database table SSPG.PNFOTO
  * This class is intented to be used together with associated Dao object.
  */

 /**
  * This sourcecode has been generated by FREE DaoGen generator version 2.4.1.
  * The usage of generated code is restricted to OpenSource software projects
  * only. DaoGen is available in http://titaniclinux.net/daogen/
  * It has been programmed by Tuomo Lukka, Tuomo.Lukka@iki.fi
  *
  * DaoGen license: The following DaoGen generated source code is licensed
  * under the terms of GNU GPL license. The full text for license is available
  * in GNU project's pages: http://www.gnu.org/copyleft/gpl.html
  *
  * If you wish to use the DaoGen generator to produce code for closed-source
  * commercial applications, you must pay the lisence fee. The price is
  * 5 USD or 5 Eur for each database table, you are generating code for.
  * (That includes unlimited amount of iterations with all supported languages
  * for each database table you are paying for.) Send mail to
  * "Tuomo.Lukka@iki.fi" for more information. Thank you!
  */



public class FImage implements Cloneable, Serializable {

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private String fimDftar;
    private String fimKeter;
    private String fimLogom;

    private byte[] imageByte;
    

    /** 
     * Constructors. DaoGen generates two constructors by default.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public FImage () {
        
       

    }

    public FImage (String fimDftarIn) {

          this.fimDftar = fimDftarIn;

    }



    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public String getFimDftar() {
          return this.fimDftar;
    }
    public void setFimDftar(String fimDftarIn) {
          this.fimDftar = fimDftarIn;
    }

    public String getFimKeter() {
          return this.fimKeter;
    }
    public void setFimKeter(String fimKeterIn) {
          this.fimKeter = fimKeterIn;
    }
    public String getFimLogom() {
          return this.fimLogom;
    }
    public void setFimLogom(String fimLogomIn) {
          this.fimLogom = fimLogomIn;
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }   


    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(String fimDftarIn,String fimKeterIn,
          String fimLogomIn) {
          this.fimDftar = fimDftarIn;
          this.fimKeter = fimKeterIn;
          this.fimLogom = fimLogomIn;
    }


    /** 
     * hasEqualMapping-method will compare two Ppfoto instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(FImage valueObject) {

          if (this.fimDftar == null) {
                    if (valueObject.getFimDftar()!= null)
                           return(false);
          } else if (!this.fimDftar.equals(valueObject.getFimDftar())) {
                    return(false);
          }
          if (valueObject.getFimKeter() != this.fimKeter) {
                    return(false);
          }
          if (valueObject.getFimLogom() != this.fimLogom) {
                    return(false);
          }
          return true;
    }



    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in textlog.
     */
    public String toString() {
        StringBuffer out = new StringBuffer(this.getDaogenVersion());
        out.append("\nclass Ppfoto, mapping to table SPBT.FIMAGE\n");
        out.append("Persistent attributes: \n"); 
        out.append("fimDftar = " + this.fimDftar + "\n"); 
        out.append("fimKeter = " + this.fimKeter + "\n"); 
        out.append("fimLogom = " + this.fimLogom+ "\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        FImage cloned = new FImage();

        if (this.fimDftar != null)
             cloned.setFimDftar(new String(this.fimDftar)); 
        cloned.setFimKeter(this.fimKeter); 
        cloned.setFimLogom(this.fimLogom); 
        return cloned;
    }



    /** 
     * getDaogenVersion will return information about
     * generator which created these sources.
     */
    public String getDaogenVersion() {
        return "DaoGen version 2.4.1";
    }

}
