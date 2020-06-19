package com.test1;
    public class IntrduceDemo {
        //姓名
        private String name;
        //年龄
        private  int  age;
        //
        public String aihao;

        private  int  money;

        public int getAge() {
            return age;
        }
        public int getMoney() {
            return money;
        }
        public  void  setMoney(int money){
            this.money = money;
        }
        public  String getaihao(){
            return aihao;
        }
        public  void setaihao(String aihao){
            this.aihao = aihao;
        }
        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * 自我介绍
         */
        public void intrduce(){
            System.out.println("您好，我叫"+this.name+"今年"+this.age+"岁！"+"喜欢"+this.aihao+"年薪"+this.money+"欧元");
        }

    }

