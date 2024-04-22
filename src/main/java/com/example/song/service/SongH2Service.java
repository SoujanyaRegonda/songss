package com.example.song.service;

import com.example.song.model.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.song.repository.SongRepository;
import org.springframework.stereotype.Service;
import com.example.song.model.SongRowMapper;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class SongH2Service implements SongRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Song> getAllSongs() {
        return (ArrayList<Song>) db.query("SELECT * FROM playlist", new SongRowMapper());
    }

    @Override
    public Song getSongById(int songId) {
        try {
            return db.queryForObject("SELECT * FROM playlist WHERE songId = ?", new SongRowMapper(), songId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    @Override
    public Song addSong(Song song) {
        db.update("INSERT INTO playlist(songName, lyricist, singer, musicDirector) values (?, ?, ?, ?)",
                song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
        return db.queryForObject("SELECT * FROM playlist WHERE songName = ? and lyricist = ?", new SongRowMapper(),
                song.getSongName(), song.getLyricist());

    }

    @Override
    public Song updateSong(int songId, Song song) {
        return new Song(3, "Butta Bomma", "Ramajogayya Sastry", "Armaan Malik", "Thaman");

    }

    @Override
    public Song deleteSong(int songId) {

    }

}