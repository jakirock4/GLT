/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.coverage;

import java.util.ArrayList;
import com.smartsoft.modules.Sensor;
import com.smartsoft.mobileObject.Point;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class ZizageCoverage {
    
    public ZizageCoverage(){}
    
    public void  coverage(ArrayList<Sensor> SensorsList,int _2xr)  
    {
        double ZigzagLineLength = _2xr * 0.751;// vertical. top re //_2xr*0.8
        double DistanceBtweenZigzagsCorners = (_2xr / 2) * 0.881; // horizontal left re.//(_2xr/2)*0.8;
        int worksideWidth = 500;
        int worksideHeight = 450;
        int Nodescount = SensorsList.size();
        int currentNode = 0;
        Double patternsDouble = worksideHeight / ZigzagLineLength/2;  //2 line levels.
        int Patterns = patternsDouble.intValue();
        Double cornersDouble = worksideWidth / DistanceBtweenZigzagsCorners;
        int corners = cornersDouble.intValue();

        for (int pattern = 0; pattern <Patterns; pattern++)
        {
            for (int corner = 0; corner <corners; corner++)
            {
                //double x = corner * DistanceBtweenZigzagsCorners;
                int x = (int) (corner * DistanceBtweenZigzagsCorners);
                int level = (2 * pattern) + 1;
                if (currentNode < Nodescount)
                {
                    if (corner % 2 != 0)// first level of zaigza
                    {
                        int y = (int) ZigzagLineLength * (level - 1);
                        SensorsList.get(currentNode).setPosition(new Point(x, y));
                        SensorsList.get(currentNode).setPox(x);
                        SensorsList.get(currentNode).setPoy(y);
                        currentNode++;
                    }
                    else // second level of zigzag
                    {
                        int y = (int) ZigzagLineLength * level;
                        SensorsList.get(currentNode).setPosition(new Point(x, y));
                        SensorsList.get(currentNode).setPox(x);
                        SensorsList.get(currentNode).setPoy(y);
                        currentNode++;
                    }
                }
                else break;
            }
        }
    }
    
    /**
     * ZigzagLineLengthRatio must be : 0.1~ 0.99
     * DistanceBtweenZigzagsCornersRaio must be: 0.1~ 0.99
     * @param SensorsList Lista de sensores
     * @param _2xr Parametro para colocar espacios entre sensores
     * @param C Parametro de medida verticalmente
     * @param V Parametro de medida horizontalmente
     */
    
    public void coverage(ArrayList<Sensor> SensorsList, int _2xr, double C, double V)  
    {
        double Li = _2xr * C;// vertical. top re //_2xr*0.8
        double D = (_2xr / 2) * V; // horizontal left re.//(_2xr/2)*0.8;
        int Nodescount = SensorsList.size();
        int currentNode = 0;
        int W = 500;
        int H = 450;
        int Zigzags = (int) ((int) H / Li/2); 
        int Corners = (int) (W / D*0.9);

        for (int zigzag = 0; zigzag <Zigzags; zigzag++)
        {
            for (int corner = 0; corner <Corners; corner++)
            {
                int x = (int) (corner * D);// horizontal
                int level = (2 * zigzag) + 1;
                if (currentNode < Nodescount)
                {
                    if (corner % 2 != 0)// first level of zaigza
                    {
                        int y = (int) (Li * (level - 1));
                        SensorsList.get(currentNode).setPosition(new Point(x, y));
                        SensorsList.get(currentNode).setPox(x);
                        SensorsList.get(currentNode).setPoy(y);
                        currentNode++;
                    }
                    else // second level of zigzag
                    {
                        int y = (int) (Li * (level));
                        SensorsList.get(currentNode).setPosition(new Point(x, y));
                        SensorsList.get(currentNode).setPox(x);
                        SensorsList.get(currentNode).setPoy(y);
                        currentNode++;
                    }
                }
                else break;
            }
        }
    }


}
