using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SoundCloud222
{
    public class Track
    {
        public Track() { }
        public Track(int i,string l) {
            id=i;
            link = l;
        }
        private int id;

        public int Id
        {
            get { return id; }
            set { id = value; }
        }

        private string link;

        public string Link
        {
            get { return link; }
            set { link = value; }
        }
    }
}