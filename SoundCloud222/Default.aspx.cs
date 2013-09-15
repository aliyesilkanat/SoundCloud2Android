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
        List<int> IDs = new List<int>();
        aliyesilkanatEntities ent = new aliyesilkanatEntities();
        protected void Page_Load(object sender, EventArgs e)
        {
            lblUyari.Visible = false;
            parcalariAl();
        }

        private void parcalariAl()
        {
            IDs.Clear();
            List<sp_ParcalariAl_Result> spRes = ent.sp_ParcalariAl().ToList();
            List<string> parcalar = new List<string>();
            foreach (sp_ParcalariAl_Result result in spRes)
            {
                IDs.Add(result.ID);
                parcalar.Add("<iframe width=\"100%\" height=\"166\" scrolling=\"no\" frameborder=\"no\"src=\"http://w.soundcloud.com/player/?url=" + result.Link + "&show_artwork=true\"></iframe>");
            }
            gridSC.DataSource = parcalar;
            gridSC.DataBind();

        }

        protected void btnEkle_Click(object sender, EventArgs e)
        {
            if (txtLink.Text != "")
            {
                ent.sp_ParcaEkle(txtLink.Text);
                txtLink.Text = "";
                parcalariAl();
      
            }
            else lblUyari.Visible = true;
        }

        protected void gridSC_RowDataBound(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.DataRow)
            {
                string decodedText = HttpUtility.HtmlDecode(e.Row.Cells[1].Text);
                e.Row.Cells[1].Text = decodedText;
            }
        }

        protected void gridSC_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            aliyesilkanatEntities entity = new aliyesilkanatEntities();
            try
            {
                entity.sp_ParcaSil(IDs[e.RowIndex]);
                parcalariAl();
            }
            catch (Exception exc)
            {
                exc.ToString();

            }
        }

      
    }
}
