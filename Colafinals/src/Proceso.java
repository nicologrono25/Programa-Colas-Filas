class Proceso {
    String idProceso;
    long cedula;
    int tiempoCPU;

    public Proceso(String idProceso, long cedula, int tiempoCPU) {
        this.idProceso = idProceso;
        this.cedula = cedula;
        this.tiempoCPU = tiempoCPU;
    }

    public String getIdProceso() {

        return idProceso;
    }

    public long getCedula() {

        return cedula;
    }

    public int getTiempoCPU() {

        return tiempoCPU;
    }

    public void setTiempoCPU(int tiempoCPU) {

        this.tiempoCPU = tiempoCPU;
    }

    @Override
    public String toString() {
        return "Proceso " + idProceso + " (Cedula: " + cedula + ", Tiempo CPU: " + tiempoCPU + " ms)";
    }
}
