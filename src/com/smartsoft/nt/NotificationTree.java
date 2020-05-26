/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartsoft.nt;

import java.util.ArrayList;
import com.smartsoft.modules.Sensor;
import com.smartsoft.modules.SensorsGroup;
import com.smartsoft.graphx.Graph;
import com.smartsoft.graphx.Subgrapgh;
import com.smartsoft.graphx.Edge;
import com.smartsoft.graphx.Vertex;
//import com.smartsoft.nt.EdgeInSubgraph;


/**
 *
 * @author Ana Karen Hérnandez Vázquez
 * @version  1.1
 */
public class NotificationTree {
    
    
    public Sensor _sensor;
    public ArrayList<SensorsGroup> MyGroups;
    public Graph myGraph;
    public NTnode Root = new NTnode();
/**
 * 
 * @param sensor Regresa el sensor en el árbol de notificaciones
 */    
    public NotificationTree(Sensor sensor)
    {

        _sensor = sensor;
        MyGroups = sensor.MyGroups;
        Root.setSensor(_sensor); // Root <-- Sj
        Root.ParentN = null;
        Root.setParentN(null);

        // obtener el gráfo
        myGraph = new Graph(sensor);

        BuildTree();
    }

/**
 * 
 * @return Regresa el nodo raiz en el árbol de notificaciones
 */
    public NTnode getNotificationTree() {
        return Root;
    }
    
/**
 * 
 * @return Regresa una lista de subgráfos
 */
    public ArrayList<Subgrapgh> GetGraphComponent()
    {
       ArrayList<Subgrapgh> compnents = new ArrayList<>();
       ArrayList<Vertex> graphVertexSet = myGraph.GetVertexSet();

       for(Vertex v : graphVertexSet)
       {
           if (v.wasVisited == false)
           {
               Subgrapgh subgraph = new Subgrapgh();
               subgraph.setVertexSet(GetSubGraph(v));
               if (!subgraph.getVertexSet().isEmpty())
               {
                   compnents.add(subgraph);
               }
           }
       }
       return compnents;
    }
/**
 * 
 * @param v Variable del vertice del nodo sensor
 * @return Regresa una lista de vertices que aún no han sido visitados para cada vertice
 */
   private ArrayList<Vertex> GetSubGraph(Vertex v)
   {
       ArrayList<Vertex> verlIst = new ArrayList<>();
       if (v.getWasVisited()==false)
       {
           verlIst.add(v);
           v.wasVisited = true;
           for(Vertex u : v.AdjacentVertices)
           {
               //verlIst.addRange(GetSubGraph(u));
               verlIst.addAll(GetSubGraph(u));
           }
       }

       return verlIst;
   }
/**
 * 
 * @param nodes Lista de nodos en el arbol de notificaciones
 * @param id Identificador de un nodo en el arbol de notificaciones
 * @return Regresa el último nodo en el árbol de notificaciones
 */
  private NTnode getNodeByID(ArrayList<NTnode> nodes,int id)
   {
      NTnode re=null;
      for(NTnode node : nodes )
      {
          if (node.getID() == id) { re = node; break; }
      }
      return re;
   }

  public void BuildTree()
  {
      // obtiene los subgráfos
      ArrayList<Subgrapgh> subgraphs = GetGraphComponent();
      for(Subgrapgh subgraph : subgraphs)
      {
          for(Vertex v : subgraph.getVertexSet()) { 
              v.wasVisited = false; 
          }// Inicializando

          //1- 	Seleccione un vértice v_min∈V_ (j, i) con el grado mínimo δ (〖G〗 _ (j, i)).
          Vertex minDegreeVertex = SelectMinDegreeVertext(subgraph);
          Link LinkList = new Link();
          LinkList.setSensor(subRootSensor(minDegreeVertex, subgraph));
          LinkList.setIsLeader(false);
          //2- y luego vincularlos, es decir, si seleccionamos s_0 como raíz, la operación de enlace será la siguiente: s_0 es el padre de s_1, s_1 será el padre de s_2 ... y s_ (x-1) será el padre de s_x. L_k = {s_0 → s_1 → ⋯ s_x}.
          if (LinkList.getID() != -1)// no first ItemYet.
              LinkNoneLeaderSensors(LinkList, minDegreeVertex, subgraph);
          //3- seleccione el nodo líder de minv
          Sensor LeaderSensor = selectLeaderSensor(minDegreeVertex, LinkList);
          if (LeaderSensor != null)
          {
              LinkLeaderSensor(LinkList, LeaderSensor);
          }
          minDegreeVertex.wasVisited = true;

          Vertex incidentVertex = getIncidenVertext(LeaderSensor, subgraph);
          while (incidentVertex != null)
          {
              LinkNoneLeaderSensors(LinkList, incidentVertex, subgraph);
              Sensor LEADERsensor = selectLeaderSensor(incidentVertex, LinkList);
              if (LEADERsensor != null)
              {
                  LinkLeaderSensor(LinkList, LEADERsensor);
              }
              incidentVertex.wasVisited = true;
              incidentVertex = getIncidenVertext(LEADERsensor, subgraph);
          }

          ArrayList<NTnode> SubTree = ConvertLinkListToTreeNodes(LinkList);
          LinkOperationDirect(SubTree);
          Root.AddChildNotification(SubTree.get(0)); // Root.childern.add(𝑇𝑗,𝑖)
          //System.out.println(Root.getChildernN());
      }

  }
/**
 * 
 * @param LinkList Variable de la liga del vertice del nodo
 * @return Regresa una lista de nodos del árbol de notificaciones
 */
  private ArrayList<NTnode> ConvertLinkListToTreeNodes(Link LinkList)  
  {
      ArrayList<NTnode> nodes = new ArrayList<>();

      while(LinkList!=null)
      {
          NTnode node = new NTnode();
          node.setSensor(LinkList.getSensor());
          nodes.add(node);
          LinkList = LinkList.Next;
      }
      return nodes;
  }

/**
 * 
 * @param leaderS Variable del sensor lider
 * @param subgraph Información del subgrafo
 * @return  Regresa un vértice
 */
  private Vertex getIncidenVertext(Sensor leaderS, Subgrapgh subgraph)
  {
      Vertex v = null;
      if (leaderS==null)
      {
          v = null;
      }
      else
      {
          for(Edge e : subgraph.getEdges() )
          {
              if(e.getLeadersSensor().getID()==leaderS.getID())
              {
                  if (e.EndVertex.wasVisited == false) { v = e.EndVertex; }
                  else if (e.startVertex.wasVisited == false) { v = e.startVertex; }
                  else v = null;
              }
          }
      }
      return v;
  }
/**
 * 
 * @param v Variable del  vertice
 * @param subRoot Variable de una liga de un nodo
 * @return Regresa el sensor lider 
 */
  private Sensor selectLeaderSensor(Vertex v, Link subRoot)
  {
      Sensor leader = null;
      for (Edge e : v.Edges)
      {
          if (e.getLeadersSensor().getID() != _sensor.getID())
          {
              if (!isAttachedToLinkList(e.getLeadersSensor(), subRoot))
              {
                  leader = e.getLeadersSensor();
              }
          }
      }
      return leader;
  }
/**
 * 
 * @param subRoot Variable de la liga para el subgrafo 
 * @param leader Variable del sensor lider
 */
   private void LinkLeaderSensor(Link subRoot,Sensor leader) 
   {
       if (subRoot.getID() == -1)
       {
           subRoot.setSensor(leader);
           subRoot.setIsLeader(true);
       }
       else if(!isAttachedToLinkList(leader, subRoot))
       {
           // link:
           Link tail = FindListTail(subRoot);
           Link newNode = new Link();
           newNode.setSensor(leader);
           newNode.setIsLeader(true);
           tail.Next = newNode;
       }
   }
/**
 * 
 * @param subRoot Variable de la liga para el subgrafo 
 * @param mindv Vertice
 * @param subgraph Variable del subgrafo
 */
  private void LinkNoneLeaderSensors(Link subRoot, Vertex mindv, Subgrapgh subgraph) 
  {
      for(Sensor sen : mindv.getGroupOfSensors().Sensors)
      {
          if (sen.getID() != _sensor.getID())
          {
              if (!isLeader(subgraph, sen))
              {
                  if (!isAttachedToLinkList(sen, subRoot))
                  {
                      // link:
                      Link tail = FindListTail(subRoot);
                      Link newNode = new Link();
                      newNode.setSensor(sen);
                      newNode.setIsLeader(false);
                      tail.Next = newNode;
                  }
              }
          }
      }
  }



/**
 * 
 * @param mindv Vertice
 * @param subgraph Variable del subgrafo
 * @return Regresa el sensor que es  raiz
 */
  public Sensor subRootSensor(Vertex mindv, Subgrapgh subgraph)
  {
      Sensor resRoot = null; 
      for(Sensor sen : mindv.getGroupOfSensors().Sensors)
      {
          if(sen.getID()!=_sensor.getID())
          {
              if(!isLeader(subgraph,sen))
              {
                  resRoot = sen;
                  break;
              }
          }
      }
      return resRoot;
  }

/**
 * 
 * @param subgraph Variable de subgrafo
 * @return Regresa el vértice de grado mínimo
 */
  public Vertex SelectMinDegreeVertext(Subgrapgh subgraph)
  {
      Vertex minv = subgraph.getVertexSet().get(0);
      int min = subgraph.getVertexSet().get(0).Edges.size();
      for(Vertex v : subgraph.getVertexSet())
      {
          if(v.Edges.size()<min)
          {
              min = v.Edges.size();
              minv = v;
          }
      }
      return minv;
  }

/**
 * 
 * @param subgraph Variable del subgrafo
 * @param sensor Información del sensor
 * @return Regresa un booleano verdadero si el sensor es lider y un Falso si no lo es
 */
  private boolean isLeader(Subgrapgh subgraph,Sensor sensor) 
  {
      boolean isLeader = false;
      for (Edge e : subgraph.getEdges())
      {
         if(sensor.getID()==e.getLeadersSensor().getID())
         {
             isLeader = true;
             break;
         }
      }
      return isLeader;
  }
/**
 * 
 * @param LinkList Variable de la liga en el subgrafo
 * @return Regresa una lista de cola
 */
  public Link FindListTail(Link LinkList)
  {
      Link tail = null;
      if(LinkList!=null)
      {
          tail = LinkList;
          while (tail.Next != null)
          {
              tail = tail.Next;
          }
      }
      return tail;
  }
/**
 * 
 * @param sensor Información del sensor
 * @param LinkList Variable de la liga en el subgrafo
 * @return Regresa un booleano verdadero si se adjunta a la lista de enlaces y un falso si no adjunta
 */  
  public boolean isAttachedToLinkList(Sensor sensor, Link LinkList)   
  {
      boolean isAdd = true;
      if (LinkList.getID() != -1)
      {
          Link Current = LinkList;
          while (Current.getSensor().getID() != sensor.getID())
          {
              if (Current.Next == null) { return isAdd = false; }
              else
              {
                  Current = Current.Next;
              }
          }
      }
      else
      {
          isAdd = false;
      }
      return isAdd;
  }


   /*
      if (_sensor.ID == 7)
      {
          List<Subgrapgh> subgraphs = GetGraphComponent();
          for(Subgrapgh subgraph : subgraphs)
          {
              List<EdgeInSubgraph> EdgesPart = SubTree(subgraph);
              if (EdgesPart.Count == 1)
              {
                  if (EdgesPart[0].ID != -1)
                  {
                      List<NTnode> Nodes = EdgesPart[0].ListofNodes;
                      Vertex startv = EdgesPart[0].VertexSet[0];
                      Vertex Endv = EdgesPart[0].VertexSet[1];
                      Sensor Leader = subgraph.Edges[0].LeadersSensor[0];// Lider.
                      List<NTnode> listNodesss = new List<NTnode>();

                      // start verttext.
                      for(Sensor s : startv.GroupOfSensors.Sensors)
                      {
                          if (s.ID != _sensor.ID)
                          {
                              if (s.ID != Leader.ID)
                              {
                                  NTnode NODE = getNodeByID(Nodes, s.ID);
                                  if (NODE != null)
                                      listNodesss.add(NODE);
                              }
                          }
                      }

                      // Agrega lider:
                      NTnode LEADERnode = getNodeByID(Nodes, Leader.ID);
                      listNodesss.add(LEADERnode);
                      // add end:
                      for(Sensor s : Endv.GroupOfSensors.Sensors)
                      {
                          if (s.ID != _sensor.ID)
                          {
                              if (s.ID != Leader.ID)
                              {
                                  NTnode NODE = getNodeByID(Nodes, s.ID);
                                  if (NODE != null)
                                      listNodesss.add(NODE);
                              }
                          }
                      }

                      LinkOperationDirect(listNodesss);
                      Root.addChild(listNodesss[0]);
                  }
                  else
                  {
                      //=-1 no edge in such node.
                      List<NTnode> Nodes = EdgesPart[0].ListofNodes;
                      LinkOperationDirect(Nodes);
                      NTnode Node = Nodes[0];// take the last one..
                      Root.addChild(Node);// el primero. el padre se agregará automaticamente.
                  }

              }
              else // more than 2
              {
                  List<EdgeInSubgraph> SortedEdges = new List<EdgeInSubgraph>();
                  EdgeInSubgraph MaxEde = selectMaxLengthEdg(EdgesPart);
                  MaxEde.wasVisited = true;
                  SortedEdges.add(MaxEde);

                  while (MaxEde != null)
                  {
                      Sensor sensor = MaxEde.ListofNodes[0].Sensor;
                      EdgeInSubgraph eascociated = FindAssociatedEdg(sensor, EdgesPart);
                      if (eascociated != null)
                      {
                          eascociated.wasVisited = true;
                          SortedEdges.add(eascociated);
                      }
                      MaxEde = eascociated;
                  }

                  // now build.


                  for (int i = 0; i < SortedEdges.Count; i++)
                  {
                      EdgeInSubgraph e = SortedEdges[i];
                      List<NTnode> nodes = e.ListofNodes;

                      if (i == 0)
                      {
                          ReverseOperation(nodes);
                          Root.addChild(nodes[nodes.Count - 1]);
                      }
                      else
                      {
                          LinkOperationDirect(nodes);
                          // link them.
                          int j = i - 1;
                          if (j >= 0)
                          {
                              if (j == 0)
                              {
                                  EdgeInSubgraph prev = SortedEdges[j];
                                  NTnode preNode = prev.ListofNodes[0];// el último nodo en anterior e.
                                  NTnode currentNode = e.ListofNodes[0]; // el primer nodo en actual e.
                                  preNode.addChild(currentNode);
                              }
                              else
                              {
                                  EdgeInSubgraph prev = SortedEdges[j];
                                  NTnode preNode = prev.ListofNodes[prev.ListofNodes.Count - 1];// el último nodo en anterior e.
                                  NTnode currentNode = e.ListofNodes[0]; // el primer nodo en actual e.
                                  preNode.addChild(currentNode);
                              }
                          }
                      }
                  }
              }

          }

      }
  }*/
/**
 * 
 * @param s Información del sensor para encontrar asociación
 * @param edgesLista de borde en el subgrafo para los sensores que han sido visitados
 * @return Regresa el borde en el subgrafo si el sensor es igual al sensor que pertenece al grupo de sensores en el vertice que no ha sido visitado
 */
    private EdgeInSubgraph FindAssociatedEdg(Sensor s, ArrayList<EdgeInSubgraph> edges)  
    {
        EdgeInSubgraph maxE = null;
        for(EdgeInSubgraph e : edges )
        {
            if (!e.wasVisited)
            {
                for(Vertex ver : e.vertexSet )
                {
                    for(Sensor ss : ver.getGroupOfSensors().Sensors)
                    {
                        if(s.getID()==ss.getID())
                        {
                            maxE = e;
                            break;

                        }
                    }
                }
            }
        }
        return maxE;
    }


/**
 * 
 * @param edges Lista de bordes en el subgrafo
 * @return Regresa el Maximo borde en el subgrafo
 */
   private EdgeInSubgraph selectMaxLengthEdg(ArrayList<EdgeInSubgraph> edges)
    {
       EdgeInSubgraph maxE = null;
       int max = 0;
       for(EdgeInSubgraph e : edges)
       {
           if(e.ListofNodes.size()>max)
           {
               max = e.ListofNodes.size();
               maxE = e;
           }
       }

       return maxE;

    }



/**
 * 
 * @param stackList Lista de bordes en el subgrafo
 * @param Sid Identificador de un nodo en el arbol de notificaciones
 * @return 
 */ 
    private boolean isPushed(ArrayList<EdgeInSubgraph> stackList, int Sid)
    {
        boolean re = false;
        for(EdgeInSubgraph stack : stackList)
            for(NTnode s : stack.ListofNodes)
            {
                if (s.getID() == Sid)
                {
                    re = true; break;
                }
            }
        return re;
    }



   /*
    private List<EdgeInSubgraph> SubTree(Subgrapgh subgraph)
    {
        List<EdgeInSubgraph> ListOfLeadersTree = new List<EdgeInSubgraph>(); 
        // Inicializando.
        foreach (Vertex v in subgraph.vertexSet)
        {
            v.wasVisited = false;
        }

        if (subgraph.Edges.Count > 0)
        {
            for(Edge e : subgraph.Edges)
            {
                EdgeInSubgraph edgeStack = new EdgeInSubgraph();
                for(Sensor s : e.LeadersSensor)
                {
                    if (s.ID != _sensor.ID)
                    {

                        if (!isPushed(ListOfLeadersTree, s.ID))
                        {
                            NTnode Sn = new NTnode();
                            Sn.Sensor = s;
                            Sn.ID = s.ID;
                            edgeStack.ID = Sn.ID;
                            edgeStack.ListofNodes.add(Sn);
                            // check the start vertext.
                            Vertex StartVertex = e.startVertex;
                            edgeStack.addVertex(StartVertex);
                            if (!StartVertex.wasVisited)
                            {

                                for(Sensor ss : StartVertex.GroupOfSensors.Sensors)
                                {
                                    if (ss.ID != _sensor.ID)
                                    {
                                        if (!isLeader(subgraph, ss))
                                        {
                                            NTnode ssn = new NTnode();
                                            ssn.Sensor = ss;
                                            ssn.ID = ss.ID;
                                            edgeStack.ListofNodes.add(ssn);

                                        }
                                    }
                                }
                                StartVertex.wasVisited = true;
                            }


                            Vertex EndVertex = e.EndVertex;
                            edgeStack.addVertex(EndVertex);
                            if (!EndVertex.wasVisited)
                            {

                                for(Sensor ss : EndVertex.GroupOfSensors.Sensors)
                                {
                                    if (ss.ID != _sensor.ID)
                                    {
                                        if (!isLeader(subgraph, ss))
                                        {
                                            NTnode ssn = new NTnode();
                                            ssn.Sensor = ss;
                                            ssn.ID = ss.ID;
                                            edgeStack.ListofNodes.add(ssn);

                                        }
                                    }
                                }
                                EndVertex.wasVisited = true;
                            }

                            ListOfLeadersTree.add(edgeStack);


                        } // end push

                    }
                }
            }
        }
        else
        {
            // sin líderes: normalmente ... solo hay un nodo
            if (subgraph.vertexSet.Count == 1)
            {
                Vertex v = subgraph.vertexSet[0];
                EdgeInSubgraph xStack = new EdgeInSubgraph();

                for(Sensor ss : v.GroupOfSensors.Sensors)
                {
                    if (ss.ID != _sensor.ID)
                    {
                        NTnode ssn = new NTnode();
                        ssn.Sensor = ss;
                        ssn.ID = ss.ID;
                        xStack.ID = -1;// NO edge here.
                        xStack.ListofNodes.add(ssn); // v no tiene líderes ..
                        xStack.addVertex(v);
                    }
                }
                v.wasVisited = true;
                ListOfLeadersTree.add(xStack);
            }
        }



        //LinkComponentsOfSubGraph(ListOfLeadersTree);// enlazar los nodos dentro de cada pila.
        return ListOfLeadersTree;
    }
   */
/**
 * 
 * @param ListNodes  Lista de nodos del árbol de notificaciones
 */
    private void ReverseOperation(ArrayList<NTnode> ListNodes)  
    {
        for(NTnode node : ListNodes)
        {
            node.RemoveNotificationChilde(node);
        }

        if (ListNodes.size() >= 2)
        {
            for (int i = ListNodes.size()-1; i >= 0; i--)
            {
                int c = i - 1;
                if(c>=0)
                {
                    ListNodes.get(i).AddChildNotification(ListNodes.get(c));
                }
            }
        }

    }
/**
 * 
 * @param ListNodes Lista de nodos del árbol de notificaciones
 */
    private void LinkOperationDirect(ArrayList<NTnode> ListNodes)
    {
        for(NTnode node : ListNodes)
        {
            node.RemoveNotificationChilde(node);
        }

        if (ListNodes.size() >= 2)
        {
            for (int i = 0; i < ListNodes.size(); i++)
            {
                int c = i + 1;
                if (c < ListNodes.size()) 
                { 
                    ListNodes.get(i).AddChildNotification(ListNodes.get(c)); 
                }
            }
        }
    }
/**
 * 
 * @param v Informción del vertice
 * @return Regresa un nodo en el árbol de notificaciones
 */
   private NTnode SubTree(Vertex v)
    {
       ArrayList<NTnode> nodes = new ArrayList<>();
       for(Sensor s : v.getGroupOfSensors().Sensors)
       {
           if (s.getID() != _sensor.getID())
           {
               NTnode node = new NTnode();
               node.setParentN(null);
               node.setSensor(s);
               nodes.add(node);
           }
       }

       if(v.getIsIsolatedVertex())
       {
           for(int i=0;i<nodes.size();i++)
           {
               int p = i - 1;
               int c = i + 1;
               if (p > -1) { nodes.get(i).setParentN(nodes.get(p)); }
               if (c < nodes.size()) { nodes.get(i).AddChildNotification(nodes.get(c)); }
           }
       }
       else
       {

       }

       NTnode xxNode=nodes.get(0);
       return xxNode; 
    }
      
}
