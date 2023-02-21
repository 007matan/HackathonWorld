package com.cohen.hackathonworld.Model;

import java.util.ArrayList;

//List of teams
public class IDsList {
    public ArrayList<Integer> ids;

    public IDsList(){}

    public IDsList withIds(ArrayList<Integer> ids){
        if(this.ids == null){
            this.ids = new ArrayList<>();
        }
        for(int i=0; i<ids.size();i++){
            this.ids.add(ids.get(i));
        }

        return this;
    }
    public IDsList withIds(){
        this.ids = new ArrayList<>();
        return  this;
    }
    public IDsList withIds(int id){
        this.ids = new ArrayList<>();
        this.addId(id);
        return this;
    }

    public void addIds(ArrayList<Integer> ids){
        this.ids.addAll(ids);
    }

    public void addId(int id){
        if(this.ids == null)
            withIds();
        this.ids.add(id);
    }

    public ArrayList<Integer> getIds() {
        return ids;
    }

    public void removeIds(ArrayList<Integer> ids){
        for(Integer i : ids){
            for(Integer j : this.ids){
                if(this.ids.get(j) == i)
                    this.ids.remove(j);
            }
        }
    }
    public void removeId(int id){
        for(Integer j : this.ids)
            if(this.ids.get(j) == id)
                this.ids.remove(j);
    }
}
