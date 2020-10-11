package com.extreme.media.entity;

public class NotaCreditoFijo {
	String tipoEmision;
	String razonSocial;
	String nombreComercial;
	String ruc;
	String claveAcceso;
	String codDoc;
	String estab;
	String ptoEmi;
	String secuencial;
	String dirMatriz;
	//infonotacredito
	String fechaEmision;
	String dirEstablecimiento;
	String tipoIdentificacionComprador;
	String razonSocialComprador;
	String identificacionComprador;
	String contribuyenteEspecial;
	String obligadoContabilidad;
	String rise;
	String codDocModificado;
	String numDocModificado;
	String fechaEmisionDocSustento;
	String totalSinImpuestos;
	String valorModificacion;
	String moneda;
	String clave_acceso_sri_nc;
	String importeTotal;
	
	
	
	
	String impuestoCodigo;
	public String getImpuestoCodigo() {
		return impuestoCodigo;
	}

	public void setImpuestoCodigo(String impuestoCodigo) {
		this.impuestoCodigo = impuestoCodigo;
	}

	public String getImpuestoCodigoPorcentaje() {
		return impuestoCodigoPorcentaje;
	}

	public void setImpuestoCodigoPorcentaje(String impuestoCodigoPorcentaje) {
		this.impuestoCodigoPorcentaje = impuestoCodigoPorcentaje;
	}

	public String getImpuestoTarifa() {
		return impuestoTarifa;
	}

	public void setImpuestoTarifa(String impuestoTarifa) {
		this.impuestoTarifa = impuestoTarifa;
	}

	public String getImpuestoBaseImponible() {
		return impuestoBaseImponible;
	}

	public void setImpuestoBaseImponible(String impuestoBaseImponible) {
		this.impuestoBaseImponible = impuestoBaseImponible;
	}

	public String getImpuestoValor() {
		return impuestoValor;
	}

	public void setImpuestoValor(String impuestoValor) {
		this.impuestoValor = impuestoValor;
	}





	String impuestoCodigoPorcentaje;
	String impuestoTarifa;
	String impuestoBaseImponible;
	String impuestoValor;
	
	String totalImpuestoCodigo;
	public String getTotalImpuestoCodigo() {
		return totalImpuestoCodigo;
	}

	public void setTotalImpuestoCodigo(String totalImpuestoCodigo) {
		this.totalImpuestoCodigo = totalImpuestoCodigo;
	}

	public String getTotalImpuestoCodigoPorcentaje() {
		return totalImpuestoCodigoPorcentaje;
	}

	public void setTotalImpuestoCodigoPorcentaje(String totalImpuestoCodigoPorcentaje) {
		this.totalImpuestoCodigoPorcentaje = totalImpuestoCodigoPorcentaje;
	}

	public String getTotalImpuestoBaseImponible() {
		return totalImpuestoBaseImponible;
	}

	public void setTotalImpuestoBaseImponible(String totalImpuestoBaseImponible) {
		this.totalImpuestoBaseImponible = totalImpuestoBaseImponible;
	}

	public String getTotalImpuestoValor() {
		return totalImpuestoValor;
	}

	public void setTotalImpuestoValor(String totalImpuestoValor) {
		this.totalImpuestoValor = totalImpuestoValor;
	}





	String totalImpuestoCodigoPorcentaje;
	String totalImpuestoBaseImponible;
	String totalImpuestoValor;
	
	public String getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(String importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getClave_acceso_sri_nc() {
		return clave_acceso_sri_nc;
	}

	public void setClave_acceso_sri_nc(String clave_acceso_sri_nc) {
		this.clave_acceso_sri_nc = clave_acceso_sri_nc;
	}





	//total con impuesto IVA verificar puede ser dinamico si utilizan otros impuesto aparte del iva
	String codigo;
	String codigoPorcentaje;
	String baseImponible;
	String valor;
	//infoAdicional
		String campoAdicionalDireccion;
		String campoAdicionalTelefono;
		String campoAdicionalEmail;
		public String getCampoAdicionalDireccion() {
			return campoAdicionalDireccion;
		}

		public void setCampoAdicionalDireccion(String campoAdicionalDireccion) {
			this.campoAdicionalDireccion = campoAdicionalDireccion;
		}

		public String getCampoAdicionalTelefono() {
			return campoAdicionalTelefono;
		}

		public void setCampoAdicionalTelefono(String campoAdicionalTelefono) {
			this.campoAdicionalTelefono = campoAdicionalTelefono;
		}

		public String getCampoAdicionalEmail() {
			return campoAdicionalEmail;
		}

		public void setCampoAdicionalEmail(String campoAdicionalEmail) {
			this.campoAdicionalEmail = campoAdicionalEmail;
		}



		
	
	String motivo;
	//general
	String ambiente;
	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getTipoEmision() {
		return tipoEmision;
	}

	public void setTipoEmision(String tipoEmision) {
		this.tipoEmision = tipoEmision;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public String getCodDoc() {
		return codDoc;
	}

	public void setCodDoc(String codDoc) {
		this.codDoc = codDoc;
	}

	public String getEstab() {
		return estab;
	}

	public void setEstab(String estab) {
		this.estab = estab;
	}

	public String getPtoEmi() {
		return ptoEmi;
	}

	public void setPtoEmi(String ptoEmi) {
		this.ptoEmi = ptoEmi;
	}

	public String getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}

	public String getDirMatriz() {
		return dirMatriz;
	}

	public void setDirMatriz(String dirMatriz) {
		this.dirMatriz = dirMatriz;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getDirEstablecimiento() {
		return dirEstablecimiento;
	}

	public void setDirEstablecimiento(String dirEstablecimiento) {
		this.dirEstablecimiento = dirEstablecimiento;
	}

	public String getTipoIdentificacionComprador() {
		return tipoIdentificacionComprador;
	}

	public void setTipoIdentificacionComprador(String tipoIdentificacionComprador) {
		this.tipoIdentificacionComprador = tipoIdentificacionComprador;
	}

	public String getRazonSocialComprador() {
		return razonSocialComprador;
	}

	public void setRazonSocialComprador(String razonSocialComprador) {
		this.razonSocialComprador = razonSocialComprador;
	}

	public String getIdentificacionComprador() {
		return identificacionComprador;
	}

	public void setIdentificacionComprador(String identificacionComprador) {
		this.identificacionComprador = identificacionComprador;
	}

	public String getContribuyenteEspecial() {
		return contribuyenteEspecial;
	}

	public void setContribuyenteEspecial(String contribuyenteEspecial) {
		this.contribuyenteEspecial = contribuyenteEspecial;
	}

	public String getObligadoContabilidad() {
		return obligadoContabilidad;
	}

	public void setObligadoContabilidad(String obligadoContabilidad) {
		this.obligadoContabilidad = obligadoContabilidad;
	}

	public String getRise() {
		return rise;
	}

	public void setRise(String rise) {
		this.rise = rise;
	}

	public String getCodDocModificado() {
		return codDocModificado;
	}

	public void setCodDocModificado(String codDocModificado) {
		this.codDocModificado = codDocModificado;
	}

	public String getNumDocModificado() {
		return numDocModificado;
	}

	public void setNumDocModificado(String numDocModificado) {
		this.numDocModificado = numDocModificado;
	}

	public String getFechaEmisionDocSustento() {
		return fechaEmisionDocSustento;
	}

	public void setFechaEmisionDocSustento(String fechaEmisionDocSustento) {
		this.fechaEmisionDocSustento = fechaEmisionDocSustento;
	}

	public String getTotalSinImpuestos() {
		return totalSinImpuestos;
	}

	public void setTotalSinImpuestos(String totalSinImpuestos) {
		this.totalSinImpuestos = totalSinImpuestos;
	}

	public String getValorModificacion() {
		return valorModificacion;
	}

	public void setValorModificacion(String valorModificacion) {
		this.valorModificacion = valorModificacion;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoPorcentaje() {
		return codigoPorcentaje;
	}

	public void setCodigoPorcentaje(String codigoPorcentaje) {
		this.codigoPorcentaje = codigoPorcentaje;
	}

	public String getBaseImponible() {
		return baseImponible;
	}

	public void setBaseImponible(String baseImponible) {
		this.baseImponible = baseImponible;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	
	
	//info adicional
	String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
