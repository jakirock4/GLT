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
public class GridCoverage {
    
    public GridCoverage(){}
    
    public  void GridCoverage1(ArrayList<Sensor> SensorsList, int _2xr)
    {
        int w = 500;
        int h = 450;
        int count = SensorsList.size();
        int s = 0;
        for (int x = 1; x < w - (_2xr / 2); x += _2xr)
        {
            for (int y = 1; y < h - (_2xr / 2); y += _2xr)
            {
                if (s < count)
                {
                    SensorsList.get(s).setPosition(new Point(x, y));
                    SensorsList.get(s).setPox(x);
                    SensorsList.get(s).setPoy(y);
                    s++;
                }
                else break;
            }
        }
    }
    
    public void GridCoverage2(ArrayList<Sensor> SensorsList, int _2xr)
        {
            // 
            // int 
            int w = 500;
            int h = 450;
            int count = SensorsList.size();
            int s = 0;

            // step 1:
            for (int x = 1; x < w - (_2xr / 2); x += _2xr)
            {
                for (int y = 1; y < h - (_2xr / 2); y += _2xr)
                {
                    if (s < count)
                    {
                        SensorsList.get(s).setPosition(new Point(x, y));
                        SensorsList.get(s).setPox(x);
                        SensorsList.get(s).setPoy(y);
                        s++;
                    }
                    else
                        break;
                }
            }

            //step 2:

            for (int xx = _2xr; xx < w - (_2xr / 2); xx += _2xr)
            {
                for (int yy = _2xr; yy < h - (_2xr / 2); yy += _2xr)
                {
                    if (s < count)
                    {
                        Point p = new Point(xx - _2xr / 2, yy - _2xr / 2);
                        s++;
                        SensorsList.get(s).setPosition(new Point(xx - _2xr / 2, yy - _2xr / 2));
                        SensorsList.get(s).setPox(xx - _2xr / 2);
                        SensorsList.get(s).setPoy(yy - _2xr / 2);
                        s++;
                    }
                    else
                        break;
                }
            }

        }
}
