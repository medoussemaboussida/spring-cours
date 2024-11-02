package tn.esprit.tp2.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.tp2.entity.Bloc;
import tn.esprit.tp2.entity.Chambre;
import tn.esprit.tp2.repository.BlocRepository;

import java.util.List;

@Repository
public class BlocService implements IBlocService{
    @Autowired
    BlocRepository blocRepository;
    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }


    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBloc(long id) {
        blocRepository.deleteById(id);
    }
}
