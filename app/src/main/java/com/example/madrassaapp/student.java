package com.example.madrassaapp;

public class student {

        private  int id;
        private String name;
        private int age;
        private String className;
        private String surah;
        private int fverse;
        private int lverse;
        private String sabqi;
        private String manzil;

        // Constructor
        public student(String name, int age, String className,String surah, int fverse, int lverse, String sabqi, String manzil){
            this.name=name;
            this.age=age;
            this.className=className;
            this.surah=surah;
            this.fverse=fverse;
            this.lverse=lverse;
            this.sabqi=sabqi;
            this.manzil=manzil;
        }

    // Getters and Setters

        public int getId() { return id; }

        public void setId(int id) { this.id = id; }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getSurah() {
            return surah;
        }

        public void setSurah(String surah) {
            this.surah = surah;
        }

        public int getFverse() {
            return fverse;
        }

        public void setFverse(int fverse) {
            this.fverse = fverse;
        }

        public int getLverse() {
            return lverse;
        }

        public void setLverse(int lverse) {
            this.lverse = lverse;
        }

        public String getSabqi() {
            return sabqi;
        }

        public void setSabqi(String sabqi) {
            this.sabqi = sabqi;
        }

        public String getManzil() {
            return manzil;
        }

        public void setManzil(String manzil) {
            this.manzil = manzil;
        }

    }


