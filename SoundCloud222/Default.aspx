<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Default.aspx.cs" Inherits="SoundCloud222.WebForm1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server"><div id="wrapper" style="height:750px;width:960px">
    <div id="main"  style="margin-left:auto;margin-right:auto;width:700px; height: 252px;">
    
        <asp:TextBox ID="txtLink" runat="server" Width="347px"></asp:TextBox>
    
        <asp:Button ID="btnEkle" runat="server" TabIndex="1" Text="Ekle" OnClick="btnEkle_Click" />
    <asp:TextBox ID="txtUyari" runat="server" Text="Hatalı Link"></asp:TextBox>
        <asp:GridView ID="gridParcalar" runat="server" CellPadding="4" ForeColor="#333333" GridLines="None">
            <AlternatingRowStyle BackColor="White" />
            <EditRowStyle BackColor="#7C6F57" />
            <FooterStyle BackColor="#1C5E55" Font-Bold="True" ForeColor="White" />
            <HeaderStyle BackColor="#1C5E55" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#666666" ForeColor="White" HorizontalAlign="Center" />
            <RowStyle BackColor="#E3EAEB" />
            <SelectedRowStyle BackColor="#C5BBAF" Font-Bold="True" ForeColor="#333333" />
            <SortedAscendingCellStyle BackColor="#F8FAFA" />
            <SortedAscendingHeaderStyle BackColor="#246B61" />
            <SortedDescendingCellStyle BackColor="#D4DFE1" />
            <SortedDescendingHeaderStyle BackColor="#15524A" />
        </asp:GridView>
    
    </div></div>
    </form>
</body>
</html>
