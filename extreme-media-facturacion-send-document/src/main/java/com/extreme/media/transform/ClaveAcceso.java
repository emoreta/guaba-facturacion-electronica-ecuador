package com.extreme.media.transform;

public class ClaveAcceso {
	private String claveGenerada;
	public String generaClave(String fechaEmision, String tipoComprobante, String ruc, String ambiente,
            String serie, String numeroComprobante, String codigoNumerico, String tipoEmision)
    {
        //System.out.println( fechaEmision+" "+  tipoComprobante+" "+ ruc+" "+ ambiente+" "+ serie+" "+ numeroComprobante+" "+codigoNumerico+" "+tipoEmision +"CLAVE GENERADA inicio");
       int verificador = 0;
       if ((ruc != null) && (ruc.length() < 13)) ruc = String.format("%013d", new Object[] { ruc });
       StringBuilder clave = new StringBuilder(fechaEmision);
       clave.append(tipoComprobante);
       clave.append(ruc);
       clave.append(ambiente);
       clave.append(serie);
       clave.append(numeroComprobante);
       clave.append(codigoNumerico);
       clave.append(tipoEmision);
       verificador = generaDigitoModulo11(clave.toString());
       clave.append(Integer.valueOf(verificador));
       this.claveGenerada = clave.toString();
   //System.out.println(claveGenerada +"CLAVE GENERADA fin");
       if (clave.toString().length() != 49) this.claveGenerada = null;
       return this.claveGenerada;
       
   }
    public int generaDigitoModulo11(String cadena)
    {
        
        int baseMultiplicador = 7;
        int[] aux = new int[cadena.length()];
        int multiplicador = 2;
        int total = 0;
        int verificador = 0;
        for (int i = aux.length - 1; i >= 0; i--) 
        {
            aux[i] = Integer.parseInt("" + cadena.charAt(i));
            aux[i] *= multiplicador;
            multiplicador++;
            if (multiplicador > baseMultiplicador) multiplicador = 2;
            total += aux[i];
      }
      if ((total == 0) || (total == 1))verificador = 0; 
      else verificador = 11 - total % 11 == 11 ? 0 : 11 - total % 11;
      if (verificador == 10) verificador = 1;
      return verificador;
    }

}
