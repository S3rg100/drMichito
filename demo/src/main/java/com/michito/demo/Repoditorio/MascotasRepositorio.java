package com.michito.demo.Repoditorio;

import java.util.HashMap;
import java.util.*;
import org.springframework.stereotype.Repository;
import com.michito.demo.Entidades.Mascota;

@Repository
public class MascotasRepositorio {
    private Map<Integer,Mascota>data = new HashMap<>();
    public MascotasRepositorio(){
        data.put(1,new Mascota("Toby", 2, 12, "limpiez", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2FPVbbIC-0M9y4fPbbCsdvAD8bcjjtbFc0NSP3lRwlWcE%2Fmtime%3A1643275542%2Fsites%2Fdefault%2Ffiles%2Flos-10-sonidos-principales-del-perro.jpg"));
        data.put(2,new Mascota("Bella", 4, 15, "vacunación", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2FpWjxYhdRnQXN2AwFwz0SBQgXynMBXL6FAkTl2T8Jxh8%2Fmtime%3A1653934912%2Fsites%2Fdefault%2Ffiles%2Fperro-playa.jpg"));
        data.put(3,new Mascota("Max", 3, 10, "desparasitación", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2FPFQ_WcDFK5XU7LC14zHY6cP1oFKcZhLZdaTx8shRVvs%2Fmtime%3A1659080181%2Fsites%2Fdefault%2Ffiles%2Fblog%2F2022%2F8%2Fperro-jugando-con-la-pelota.jpg"));
        data.put(4,new Mascota("Luna", 1, 8, "chequeo", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2F4b5VeZ9fC4n1_aJ7D37_vCJ-PgRt1QFjO4mN6EERgWg%2Fmtime%3A1636520457%2Fsites%2Fdefault%2Ffiles%2Fperro-mirando-camara.jpg"));
        data.put(5,new Mascota("Rocky", 5, 20, "limpiez", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2F7iEME_ZLKPyzOtC2BrSODg7z9V4-PEWpVt7NqCUqWpU%2Fmtime%3A1662047106%2Fsites%2Fdefault%2Ffiles%2Fblog%2F2022%2F9%2Fperro-mirando-camara_0.jpg"));
        data.put(6,new Mascota("Milo", 6, 18, "vacunación", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2Fwn66UGZ3bRyRSnDe8oFFaIbTKSE5e_wgynGFrFzZR1M%2Fmtime%3A1662033537%2Fsites%2Fdefault%2Ffiles%2Fperro-cachorro.jpg"));
        data.put(7,new Mascota("Daisy", 2, 14, "desparasitación", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2F3SzsHBYZYOAK82W-k6TtfH1vHrRDT6TxMQijOPiv_4s%2Fmtime%3A1656942147%2Fsites%2Fdefault%2Ffiles%2Fperro-tumbado-suelo.jpg"));
        data.put(8,new Mascota("Charlie", 3, 16, "chequeo", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2FtqYPawAJlB0HKw7pGe0ZJ5BMQod5NOJ8_JrRUjxjUtI%2Fmtime%3A1629996171%2Fsites%2Fdefault%2Ffiles%2Fperro-feliz-corriendo.jpg"));
        data.put(9,new Mascota("Buddy", 4, 12, "limpiez", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2F3L1yxFdFV1fxXyBoIMQmJhOUu1bsbDp1umVmEmdL7g8%2Fmtime%3A1661320592%2Fsites%2Fdefault%2Ffiles%2Fblog%2F2022%2F8%2Fperro-orejas-grandes.jpg"));
        data.put(10,new Mascota("Coco", 5, 19, "vacunación", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2F3L6kXUJ6MFR5kIicVzIlXqzCQZkXkxR1lEloGoQ2wLk%2Fmtime%3A1640899187%2Fsites%2Fdefault%2Ffiles%2Fblog%2Fperro-en-la-nieve.jpg"));
        data.put(11,new Mascota("Lucy", 1, 9, "desparasitación", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2FpE7S8iKUbGR9YQLFLRMgoeIHbpdMSouqTkwwFP0p5_o%2Fmtime%3A1624601605%2Fsites%2Fdefault%2Ffiles%2Fperro-jugando-con-pelota.jpg"));
        data.put(12,new Mascota("Bailey", 6, 22, "chequeo", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2F9BB2JtGUSysM_TZMy6RxVZMBpXTsn8TpMLK9n0qlWn0%2Fmtime%3A1619085154%2Fsites%2Fdefault%2Ffiles%2Fblog%2F2021%2F04%2Fperro-mirando-horizonte.jpg"));
        data.put(13,new Mascota("Maggie", 2, 11, "limpiez", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2FOTL9bBbtVmGxN0EHEHx1NfJ8-8Dw2ZQ0N83dJ8nNhtw%2Fmtime%3A1619703345%2Fsites%2Fdefault%2Ffiles%2Fperro-relajado.jpg"));
        data.put(14,new Mascota("Sophie", 4, 13, "vacunación", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2Fboq65d7tih59id89-xkByPZlbgVkrbDD6CMzMGgSo-Q%2Fmtime%3A1646942250%2Fsites%2Fdefault%2Ffiles%2Fperro-1.jpg"));
        data.put(15,new Mascota("Rex", 3, 17, "desparasitación", "https://www.google.com/imgres?q=perro&imgurl=https%3A%2F%2Fstatic.fundacion-affinity.org%2Fcdn%2Ffarfuture%2F1bhOs1PZsM5MwrrYyotPIGTRmEo8diE5VyaOdZasntk%2Fmtime%3A1666270178%2Fsites%2Fdefault%2Ffiles%2Fperro-cachorro-jugando.jpg"));
    }
    public Mascota findById(int id){
        return data.get(id);
    }
    public Collection<Mascota> findAll(){
        return data.values();
    }

}
