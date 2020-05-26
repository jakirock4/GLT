/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.mobileObject;

import com.smartsoft.memory.Message;
import java.util.Comparator;

/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
class MessageSorterByTime implements Comparator<Message> 
{ 
    // Used for sorting in ascending order of 
    // roll name 
    public int compare(Message x, Message y) 
    { 
        return Integer.compare(x.getTime(), y.getTime());
    } 
} 