package com.study.oop_3;
interface Player{
    void play();
    default void pause(){
        System.out.println("暂停播放");//就是为了子类可以直接继承这个方法而不用重写
    }
}
class AudioPlayer implements Player{
    @Override
    public void play() {
        System.out.println("AudioPlayer");
    }
}
class VideoPlayer implements Player{
    @Override
    public void play() {
        System.out.println("VideoPlayer");
    }
}
public class Test3_1_6 {
    public static void main(String[] args) {
        Player ps = new AudioPlayer();
        Player ps2 = new VideoPlayer();
        ps.play();
        ps2.play();
        ps.pause();
        ps2.pause();



    }
}
