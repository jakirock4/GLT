/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.mobileObject;

import com.smartsoft.memory.Message;
import com.smartsoft.memory.TimeInterval;
import com.smartsoft.modules.Sensor;
import java.util.ArrayList;
import java.util.SortedSet; 
import java.util.TreeSet;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class TrajectoryControl {
    
    private int ObjectID;
    private ArrayList<Sensor> MyNetwork;
    private ArrayList<Message> Messages;
    public  ArrayList<TimeInterval> TimeIntervals = new ArrayList<TimeInterval>();
    
    public TrajectoryControl( int _ObjectID, ArrayList<Sensor> _MyNetwork)
    {
        this.ObjectID = _ObjectID;
        this.MyNetwork=_MyNetwork;
        this.Messages= GetMyTrajectory();
    }

    public ArrayList<Message> GetMyTrajectory()
    {
        ArrayList<Message> re = new ArrayList<Message>();
        int ID = 0;
        for(Sensor sensor : this.MyNetwork)
        {
            for(Message message : sensor.ObjectsRecords)
            {

                if(message.getObjectID()==ObjectID)
                {
                    message.setID(ID);
                    re.add(message);
                    ID++;
                }
            }
        }

        MessageSorterByTime sorter = new MessageSorterByTime();
        re.sort(sorter);

        return re;
    }

    public void FindIntervals()
    {
        TimeIntervals.clear();
        SortedSet<Integer> NodesIDs = new TreeSet<>();
        for (Message message : Messages)
        {
            boolean isFouded = isFound(message.getNodeID(), NodesIDs);
            if (!isFouded)
            {
                NodesIDs.add(message.getNodeID());
            }
        }


        for (int id : NodesIDs)
        {
            TimeInterval timeinterval = new TimeInterval();
            timeinterval.NodeID = id;
            for (Message message : Messages)
            {
                if (message.getID() == id)
                {
                    timeinterval.Messages.add(message);
                }
            }
            TimeIntervals.add(timeinterval);
        }
    }

    private boolean isFound(int Node_id, SortedSet<Integer> Nodes)
    {
        boolean re = false;
        for (int id : Nodes)
        {
            if (Node_id == id)
            {
                re = true; break;
            }
        }
        return re;
    }
    
}
