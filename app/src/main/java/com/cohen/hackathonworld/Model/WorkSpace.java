package com.cohen.hackathonworld.Model;

import java.util.ArrayList;

/**
 * Project WorkSpace - Create WorkSpace for team
 * it could be 5 or more projects(sprints) in workspace
 */
public class WorkSpace extends IDsList{

    public String workSpaceName = "";

    public WorkSpace(){
    }
    public WorkSpace(String workSpaceName){
        this.workSpaceName = workSpaceName;
        super.withIds();
    }

    public void addIds(ArrayList<Integer> projectsIds){
        super.addIds(projectsIds);
    }
    public void addId(int projectsId){
        super.addId(projectsId);
    }

    public String getWorkSpaceName() {
        return workSpaceName;
    }

    public void setWorkSpaceName(String workSpaceName) {
        this.workSpaceName = workSpaceName;
    }

    public void removeProjectId(int projectsId){
        super.removeId(projectsId);
    }

    public ArrayList<Integer> getProjectsIds() {
        return super.getIds();
    }


}
