package client.metier;

import java.io.FileInputStream;


//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

public class Son
{
        private String url;
        //private AudioStream son;


        public Son (String url)
        {
                this.url =url;
        }

        public void play()
        {
          /*      try{this.son = new AudioStream(new FileInputStream(url) );}catch(Exception ex){}
                AudioPlayer.player.start(this.son);*/
        }

        public void stop()
        {
                //AudioPlayer.player.stop(this.son);
        }

}