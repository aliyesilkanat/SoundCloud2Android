﻿//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace SoundCloud222
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    using System.Data.Objects;
    using System.Data.Objects.DataClasses;
    using System.Linq;
    
    public partial class aliyesilkanatEntities : DbContext
    {
        public aliyesilkanatEntities()
            : base("name=aliyesilkanatEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public DbSet<Parcalar> Parcalars { get; set; }
    
        public virtual int sp_ParcaEkle(string parcaLink)
        {
            var parcaLinkParameter = parcaLink != null ?
                new ObjectParameter("parcaLink", parcaLink) :
                new ObjectParameter("parcaLink", typeof(string));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction("sp_ParcaEkle", parcaLinkParameter);
        }
    
        public virtual ObjectResult<sp_ParcalariAl_Result> sp_ParcalariAl()
        {
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction<sp_ParcalariAl_Result>("sp_ParcalariAl");
        }
    
        public virtual int sp_ParcaSil(Nullable<int> iD)
        {
            var iDParameter = iD.HasValue ?
                new ObjectParameter("ID", iD) :
                new ObjectParameter("ID", typeof(int));
    
            return ((IObjectContextAdapter)this).ObjectContext.ExecuteFunction("sp_ParcaSil", iDParameter);
        }
    }
}
