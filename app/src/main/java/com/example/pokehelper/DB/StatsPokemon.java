package com.example.pokehelper.DB;

public class StatsPokemon {
    private int pokemonId;
    private int ps;
    private int atq;
    private int def;
    private int sp_atq;
    private int sp_def;
    private int vel;
    private int total;

    public StatsPokemon(){

    }

    public StatsPokemon(int pokemonId, int ps, int atq, int def, int sp_atq, int sp_def, int vel) {
        this.pokemonId = pokemonId;
        this.ps = ps;
        this.atq = atq;
        this.def = def;
        this.sp_atq = sp_atq;
        this.sp_def = sp_def;
        this.vel = vel;
        this.total = ps+atq+def+sp_atq+sp_def+vel;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getAtq() {
        return atq;
    }

    public void setAtq(int atq) {
        this.atq = atq;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSp_atq() {
        return sp_atq;
    }

    public void setSp_atq(int sp_atq) {
        this.sp_atq = sp_atq;
    }

    public int getSp_def() {
        return sp_def;
    }

    public void setSp_def(int sp_def) {
        this.sp_def = sp_def;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "StatsPokemon{" +
                "pokemonId=" + pokemonId +
                ", ps=" + ps +
                ", atq=" + atq +
                ", def=" + def +
                ", sp_atq=" + sp_atq +
                ", sp_def=" + sp_def +
                ", vel=" + vel +
                ", total=" + total +
                '}';
    }
}
