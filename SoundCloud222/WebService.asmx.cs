﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace SoundCloud222
{
    /// <summary>
    /// Summary description for WebService
    /// </summary>
    [WebService(Namespace = "http://aliyesilkanat.somee.com/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService : System.Web.Services.WebService
    {
        [WebMethod]
        public List<Track> getLinks()
        {
            List <Track> l=new List<Track>();
            aliyesilkanatEntities e = new aliyesilkanatEntities();
            foreach (sp_ParcalariAl_Result res in e.sp_ParcalariAl())
                l.Add(new Track(res.ID,res.Link));
            return l;
        }
        [WebMethod]
        public Track deleteLink(int id)
        {
            aliyesilkanatEntities ent = new aliyesilkanatEntities();
            sp_BirParcayiCek_Result res=ent.sp_BirParcayiCek(id).First();
            ent.sp_ParcaSil(id);
            return new Track(res.ID,res.Link);
        }
        
      
    }
   
}
