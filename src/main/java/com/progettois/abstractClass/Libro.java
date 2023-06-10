package com.progettois.abstractClass;

public abstract class Libro {
	
	protected long codiceISBN;
	protected String titolo, autori, casaEditrice, genere;
	protected double prezzo;
	
	public Libro(long codiceISBN, String titolo, String autori, String casaEditrice, String genere, Double prezzo) {
		
		this.codiceISBN = codiceISBN;
		this.titolo = titolo;
		this.autori = autori;
		this.casaEditrice = casaEditrice;
		this.genere = genere;
		this.prezzo = prezzo;
		
	}
	
	public long getCodiceISBN(){

        return codiceISBN;

    }

    public void setCodiceISBN(long codiceISBN){

        this.codiceISBN = codiceISBN;

    }

    public String getTitolo(){ 
        
        return titolo;
    }

    public void setTitolo(String titolo){

        this.titolo = titolo;

    }

    public String getAutori(){

        return autori;

    }

    public void setAutori(String autori){

        this.autori = autori;

    }

    public String getCasaEditrice(){

        return casaEditrice;

    }

    public void setCasaEditrice(String casaEditrice){

        this.casaEditrice = casaEditrice;

    }

    public String getGenere(){

        return genere;

    }

    public void setGenere(String genere){

        this.genere = genere;

    }

    public double getPrezzo(){

        return prezzo;

    }

    public void setPrezzo(double prezzo){

        this.prezzo = prezzo;

    }

    @Override
	public String toString(){
	 return "Codice ISBN: " + codiceISBN + "\n| Titolo: " + titolo + "\n| Autori: " + autori + "\n| Casa editrice: "
		+ casaEditrice + "\n| Genere: " + genere + "\n| Prezzo: " + prezzo;
	}

    
}
