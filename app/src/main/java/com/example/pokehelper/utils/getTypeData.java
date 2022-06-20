package com.example.pokehelper.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class getTypeData {

    public static List<String> getWeak(String str){
        List<String> weak = new ArrayList<>();
        if (str.equals("Normal")){
            weak.add("Lucha");
        } else if (str.equals("Fuego")){
            weak.addAll(Arrays.asList("Agua", "Tierra", "Roca"));
        } else if (str.equals("Agua")){
            weak.addAll(Arrays.asList("Planta", "Electrico"));
        } else if (str.equals("Planta")){
            weak.addAll(Arrays.asList("Fuego", "Hielo", "Veneno", "Volador", "Bicho"));
        } else if (str.equals("Electrico")){
            weak.add("Tierra");
        } else if (str.equals("Hielo")){
            weak.addAll(Arrays.asList("Acero", "Fuego", "Lucha", "Roca"));
        } else if (str.equals("Lucha")){
            weak.addAll(Arrays.asList("Volador", "Psiquico", "Hada"));
        } else if (str.equals("Veneno")){
            weak.addAll(Arrays.asList("Tierra", "Psiquico"));
        } else if (str.equals("Tierra")){
            weak.addAll(Arrays.asList("Agua", "Hielo", "Planta"));
        } else if (str.equals("Volador")){
            weak.addAll(Arrays.asList("Electrico", "Hielo", "Roca"));
        } else if (str.equals("Psiquico")){
            weak.addAll(Arrays.asList("Bicho", "Fantasma", "Siniestro"));
        } else if (str.equals("Bicho")){
            weak.addAll(Arrays.asList("Fuego", "Volador", "Roca"));
        } else if (str.equals("Roca")){
            weak.addAll(Arrays.asList("Acero", "Agua", "Planta", "Lucha", "Tierra"));
        } else if (str.equals("Fantasma")){
            weak.addAll(Arrays.asList("Fantasma", "Siniestro"));
        } else if (str.equals("Dragon")){
            weak.addAll(Arrays.asList("Hielo", "Dragon", "Hada"));
        } else if (str.equals("Siniestro")){
            weak.addAll(Arrays.asList("Lucha", "Bicho", "Hada"));
        } else if (str.equals("Acero")){
            weak.addAll(Arrays.asList("Lucha", "Tierra", "Fuego"));
        } else if (str.equals("Hada")){
            weak.addAll(Arrays.asList("Veneno", "Acero"));
        }
        return weak;
    }

    public static List<String> getResist(String str){
        List<String> resist = new ArrayList<>();
        if (str.equals("Fuego")){
            resist.addAll(Arrays.asList("Acero", "Fuego", "Planta", "Hielo", "Bicho", "Hada"));
        } else if (str.equals("Agua")){
            resist.addAll(Arrays.asList("Acero", "Fuego", "Agua", "Hielo"));
        } else if (str.equals("Planta")){
            resist.addAll(Arrays.asList("Agua", "Planta", "Tierra", "Electrico"));
        } else if (str.equals("Electrico")){
            resist.addAll(Arrays.asList("Acero", "Electrico", "Volador"));
        } else if (str.equals("Hielo")){
            resist.add("Hielo");
        } else if (str.equals("Lucha")){
            resist.addAll(Arrays.asList("Bicho", "Roca", "Siniestro"));
        } else if (str.equals("Veneno")){
            resist.addAll(Arrays.asList("Bicho", "Planta", "Lucha", "Veneno", "Hada"));
        } else if (str.equals("Tierra")){
            resist.addAll(Arrays.asList("Veneno", "Roca"));
        } else if (str.equals("Volador")){
            resist.addAll(Arrays.asList("Planta", "Lucha", "Bicho"));
        } else if (str.equals("Psiquico")){
            resist.addAll(Arrays.asList("Lucha", "Psiquico"));
        } else if (str.equals("Bicho")){
            resist.addAll(Arrays.asList("Planta", "Lucha", "Tierra"));
        } else if (str.equals("Roca")){
            resist.addAll(Arrays.asList("Normal", "Fuego", "Veneno", "Volador"));
        } else if (str.equals("Fantasma")){
            resist.addAll(Arrays.asList("Veneno", "Bicho"));
        } else if (str.equals("Dragon")){
            resist.addAll(Arrays.asList("Fuego", "Agua", "Planta", "Electrico"));
        } else if (str.equals("Siniestro")){
            resist.addAll(Arrays.asList("Fantasma", "Siniestro"));
        } else if (str.equals("Acero")){
            resist.addAll(Arrays.asList("Normal", "Volador", "Roca", "Bicho", "Acero", "Planta", "Psiquico", "Hielo", "Dragon", "Hada"));
        } else if (str.equals("Hada")){
            resist.addAll(Arrays.asList("Lucha", "Bicho", "Siniestro"));
        }
        return resist;
    }

    public static List<String> getStrong(String str){
        List<String> strong = new ArrayList<>();
        if (str.equals("Fuego")){
            strong.addAll(Arrays.asList("Acero", "Planta", "Hielo", "Bicho"));
        } else if (str.equals("Agua")){
            strong.addAll(Arrays.asList("Fuego", "Tierra", "Roca"));
        } else if (str.equals("Planta")){
            strong.addAll(Arrays.asList("Agua", "Tierra", "Roca"));
        } else if (str.equals("Electrico")){
            strong.addAll(Arrays.asList("Agua", "Volador"));
        } else if (str.equals("Hielo")){
            strong.addAll(Arrays.asList("Planta", "Tierra", "Volador", "Dragon"));
        } else if (str.equals("Lucha")){
            strong.addAll(Arrays.asList("Acero", "Normal", "Hielo", "Roca", "Siniestro"));
        } else if (str.equals("Veneno")){
            strong.addAll(Arrays.asList("Planta", "Hada"));
        } else if (str.equals("Tierra")){
            strong.addAll(Arrays.asList("Acero", "Fuego", "Electrico", "Veneno", "Roca"));
        } else if (str.equals("Volador")){
            strong.addAll(Arrays.asList("Planta", "Lucha" , "Bicho"));
        } else if (str.equals("Psiquico")){
            strong.addAll(Arrays.asList("Lucha", "Veneno"));
        } else if (str.equals("Bicho")){
            strong.addAll(Arrays.asList("Planta", "Psiquico", "Siniestro"));
        } else if (str.equals("Roca")){
            strong.addAll(Arrays.asList("Fuego", "Hielo", "Volador", "Bicho"));
        } else if (str.equals("Fantasma")){
            strong.addAll(Arrays.asList("Psiquico", "Fantasma"));
        } else if (str.equals("Dragon")){
            strong.add("Dragon");
        } else if (str.equals("Siniestro")){
            strong.addAll(Arrays.asList("Fantasma", "Psiquico"));
        } else if (str.equals("Acero")){
            strong.addAll(Arrays.asList("Roca", "Hielo", "Hada"));
        } else if (str.equals("Hada")){
            strong.addAll(Arrays.asList("Lucha", "Dragon", "Siniestro"));
        }
        return strong;
    }

    public static List<String> getImmune(String str) {
        List<String> immune = new ArrayList<>();
        if (str.equals("Normal")){
            immune.add("Fantasma");
        } else if (str.equals("Tierra")){
            immune.add("Electrico");
        } else if (str.equals("Volador")){
            immune.add("Tierra");
        } else if (str.equals("Fantasma")){
            immune.addAll(Arrays.asList("Normal", "Lucha"));
        } else if (str.equals("Siniestro")){
            immune.add("Psiquico");
        } else if (str.equals("Acero")){
            immune.add("Veneno");
        } else if (str.equals("Hada")){
            immune.add("Dragon");
        }
        return immune;
    }
}
