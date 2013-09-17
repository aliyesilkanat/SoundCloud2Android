using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace SoundCloud222
{
    /// <summary>
    /// Summary description for WebService
    /// </summary>
    [WebService(Namespace = "http://aliyesilkanat.somee.com")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService : System.Web.Services.WebService
    {
        [WebMethod]
        public List<string> getLinks()
        {
            List <string> l=new List<string>();
            aliyesilkanatEntities e = new aliyesilkanatEntities();
            foreach (sp_ParcalariAl_Result res in e.sp_ParcalariAl())
                l.Add(res.Link);
            return l;
        }
        [WebMethod]
        public void deleteLink(int id)
        {
            aliyesilkanatEntities e = new aliyesilkanatEntities();
            e.sp_ParcaSil(id);
        }
        
      
    }
   
}
