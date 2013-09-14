using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace SoundCloud222
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        aliyesilkanatEntities ent = new aliyesilkanatEntities();
        protected void Page_Load(object sender, EventArgs e)
        {
            txtUyari.Visible = false; 
            gridParcalar.DataSource = ent.sp_ParcalariAl();
            gridParcalar.DataBind();
            
        }

        protected void btnEkle_Click(object sender, EventArgs e)
        {
            if (txtLink.Text != "")
            {
                ent.sp_ParcaEkle(txtLink.Text);
                txtLink.Text = "";
                
                gridParcalar.DataSource = ent.sp_ParcalariAl();
                gridParcalar.DataBind();
            }
            else txtUyari.Visible = true;
        }
    }
}
//https://soundcloud.com/larslb/peteblas-message-to-the-people