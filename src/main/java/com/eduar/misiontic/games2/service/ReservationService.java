package com.eduar.misiontic.games2.service;



import com.eduar.misiontic.games2.entities.Reservation;
import com.eduar.misiontic.games2.entities.custom.CountClient;
import com.eduar.misiontic.games2.entities.custom.StatusAmount;
import com.eduar.misiontic.games2.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int reservationId){
        return reservationRepository.getReservas(reservationId);
    }

    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation()==null){
            return reservationRepository.save(reservation);
        }else{
            Optional<Reservation> e= reservationRepository.getReservas(reservation.getIdReservation());
            if(e.isEmpty()){
                return reservationRepository.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> e= reservationRepository.getReservas(reservation.getIdReservation());
            if(!e.isEmpty()){
                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                if(reservation.getGame()!=null){
                    e.get().setGame(reservation.getGame());
                }
                if(reservation.getClient()!=null){
                    e.get().setClient(reservation.getClient());
                }
                if(reservation.getScore()!=null){
                    e.get().setScore(reservation.getScore());
                }
                reservationRepository.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId){
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    public List<CountClient> getTopClient() {
        return reservationRepository.getTopClient();
    }

    public StatusAmount getStatusReport() {
        List<Reservation> completed = reservationRepository.getReservationsByDescription("completed");
        List<Reservation> cancelled = reservationRepository.getReservationsByDescription("cancelled");
        StatusAmount statusAmount = new StatusAmount(completed.size(), cancelled.size());
        return statusAmount;
    }

    public List<Reservation> getReservationPeriod(String startDateString, String devolutionDateString) {


        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = new Date();
        Date devolutionDate = new Date();
        try {
            startDate = parser.parse(startDateString);
            devolutionDate = parser.parse(devolutionDateString);
        } catch (ParseException e) {
        }
        if (startDate.before(devolutionDate)) {
            return reservationRepository.getReservationsPeriod(startDate, devolutionDate);
        }
        return new ArrayList<>();


    }
}
