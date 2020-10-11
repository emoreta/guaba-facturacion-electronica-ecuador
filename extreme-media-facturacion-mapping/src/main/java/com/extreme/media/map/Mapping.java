package com.extreme.media.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.extreme.media.entity.ExternalPostgres;
import com.extreme.media.postgress.InformacionFactura;

public class Mapping {
	

	
	
	public ExternalPostgres JoinTags(ResultSet ifactura ) throws SQLException
	{
		ExternalPostgres ep=new ExternalPostgres();
		
		//while (ifactura.next()) {
			
		    //System.out.println(rsc.getString(2));
		    //ep.
		      ep.fctrcdgo=ifactura.getString(1);
			  ep.bncocdgo=ifactura.getString(2);
			  ep.tptjcdgo=ifactura.getString(3);
			  ep.tppgcdgo=ifactura.getString(4);
			  ep.cajacdgo=ifactura.getString(5);
			  ep.usrocdgo=ifactura.getString(6);
			  ep.etdocdgo=ifactura.getString(7);
			  ep.cjabcdgo=ifactura.getString(8);
			  ep.clntcdgo=ifactura.getString(9);
			  ep.tpfccdgo=ifactura.getString(10);
			  ep.tpvncdgo=ifactura.getString(11);
			  ep.mesacdgo=ifactura.getString(12);
			  ep.fctrrbpd=ifactura.getString(13);
			  ep.fctrenpd=ifactura.getString(14);
			  ep.fctrprsn=ifactura.getString(15);
			  ep.fctrtlef=ifactura.getString(16);
			  ep.fctrdire=ifactura.getString(17);
			  ep.fctrfcha=ifactura.getString(18);
			  ep.fctrsbtt=ifactura.getString(19);
			  ep.fctr_iva=ifactura.getString(20);
			  ep.fctrtrnp=ifactura.getString(21);
			  ep.fctrretn=ifactura.getString(22);
			  ep.fctrpgdo=ifactura.getString(23);
			  ep.fctrabno=ifactura.getString(24);
			  ep.fctrchqe=ifactura.getString(25);
			  ep.fctrmoto=ifactura.getString(26);
			  ep.fctretdo=ifactura.getString(27);
			  ep.fctrobsr=ifactura.getString(28);
			  ep.fctregrs=ifactura.getString(29);
			  ep.fctrpddo=ifactura.getString(30);
			  ep.fctrfctj=ifactura.getString(31);
			  ep.fctrnmtj=ifactura.getString(32);
			  ep.fctrfcen=ifactura.getString(33);
			  ep.tpencdgo=ifactura.getString(34);
			  ep.fctrnmpr=ifactura.getString(35);
			  ep.fctrtjpr=ifactura.getString(36);
			  ep.fctrcdch=ifactura.getString(37);
			  ep.fctrcnch=ifactura.getString(38);
			  ep.fctrsoya=ifactura.getString(39);
			  ep. fctrmayo=ifactura.getString(40);
			  ep. fctrtene=ifactura.getString(41);
			  ep. fctrcuch=ifactura.getString(42);
			  ep. fctrtoma=ifactura.getString(43);
			  ep. fctr_aji=ifactura.getString(44);
			  ep. fctrcubi=ifactura.getString(45);
			  ep. fctrvaso=ifactura.getString(46);
			  ep. fctrplat=ifactura.getString(47);
			  ep. fctrmesa=ifactura.getString(48);
			  ep. fctrnmro=ifactura.getString(49);
			  ep. vntanfla=ifactura.getString(50);
			  ep. fctrorde=ifactura.getString(51);
			  ep. cdgolcl=ifactura.getString(52);
			  ep. autorizacion=ifactura.getString(53);
			  ep. usuarioid=ifactura.getString(54);
			  ep. clnt_ruc=ifactura.getString(55);
			  ep. fctr_autorizacionfac=ifactura.getString(56);
			  ep. fctr_claveaccesofac=ifactura.getString(57);
			  ep. fctr_fechautorizacionfac=ifactura.getString(58);
			  ep. fctr_estadofac=ifactura.getString(59);
			  ep. fctr_motivorechazo=ifactura.getString(60);
			  ep. fctrpasaporte=ifactura.getString(61);
			  ep. fctrpasaportecorreo=ifactura.getString(62);
			  ep. fctrdesc=ifactura.getString(63);
			  ep. fctrdescnum=ifactura.getString(64);
			  ep. fctrdescu=ifactura.getString(65);
			  ep. fctrsolidario=ifactura.getString(66);
			  ep. fctrdescsolidario=ifactura.getString(67);
			  ep. fctr_fidedescu=ifactura.getString(68);
			  ep. fecha_reporte=ifactura.getString(69);
			  
			  System.out.println("FECHA REPORTE"+ep.fctrcdgo);
		//}
		return ep;
	}

}
