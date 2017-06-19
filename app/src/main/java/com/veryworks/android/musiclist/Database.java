package com.veryworks.android.musiclist;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 6/19/2017.
 */

// 음악 목록이 저장되어 있는 저장소를 컨트롤 하는 객체
public class Database {

    // 데이터를 수정하는 함수
    // 음악제목, 앨범이름 등을 변경
    public void update(){

    }
    // 음원 데이터를 삭제하는 함수
    public void delete(){

    }
    // 음원 데이터를 읽어오는 함수
    public List<Music> read(Context context){
        // 1. 읽어올 데이터가 뭔지 알아야 됨?
        //    읽어올 데이터 = 음악파일
        // 2. 음악파일을 읽어오기 위한 도구 선정 -> 일반적인 프로그래밍에서 File I/O
        //                                      안드로이드에서 Content Resolver

        // 가. 읽어올 데이터의 주소를 설정
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        // 나. 읽어올 데이터의 구체적인 속성(Column)을 정의
        String projection[] = {
                MediaStore.Audio.Media._ID
                , MediaStore.Audio.Media.TITLE
                , MediaStore.Audio.Media.ALBUM_ID
                , MediaStore.Audio.Media.ARTIST
        };

        // 다. 위에 정의된 주소와 설정값으로 목록을 가져오는 Query(질의)를 한다.
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(musicUri, projection, null, null, null);

        // cursor 는 유지하는 비용이 크다...
        // Database 커넥션도 동일하게 연결 유지에 사용되는 비용이 아주크다
        // 그래서 데이터베이스에서 제공되는 연결객체는 사용 후 즉시 반환하는 것이
        // 성능향상에 도움이 된다.

        // 라. 반복문을 돌면서 cursor 에 있는 데이터를 다른 저장소에 저장한다.
        ArrayList<Music> datas = new ArrayList<>();

        while(cursor != null && cursor.moveToNext()){
            Music music = new Music();
            // 코드완성 하세요
//            music.id = // <- 커서에서 id 를 꺼내서 담는다.
//            // ...

            datas.add(music);
        }

        cursor.close();

        return datas;
    }
}

class Music {
    String id;
    String title;
    String albumId;
    String artist;
}