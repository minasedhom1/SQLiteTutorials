package com.example.mido.sqlitetutorials;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ViewTransactions extends AppCompatActivity {
   ListView listView;
    TextView totBPrice_tv, totSPrice_tv,totProf_tv;
    Double totBuy,totSell,totProfit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);

      //  SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView search = (SearchView) findViewById(R.id.search_view);

    //    search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));


        totBPrice_tv = (TextView) findViewById(R.id.total_buy_price_tv);
        totSPrice_tv = (TextView) findViewById(R.id.total_sell_price_tv);
        totProf_tv= (TextView) findViewById(R.id.total_profit_tv);
        listView= (ListView) findViewById(R.id.list_view);
        DBHandler dbHandler=new DBHandler(this);
         final List<Transaction> transactions=dbHandler.getAllContacts();
        final CustomAdapter adapter=new CustomAdapter(this,android.R.layout.simple_list_item_1,transactions);
        listView.setAdapter(adapter);
        totBuy=dbHandler.getTotalBuyPrice();
        totSell=dbHandler.getTotalSellPrice();
        totProfit=totSell-totBuy;
        totBPrice_tv.setText(""+totBuy);
        totSPrice_tv.setText(""+ totSell);
        totProf_tv.setText(""+totProfit);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Transaction transaction= (Transaction) listView.getAdapter().getItem(i);

                final Dialog nagDialog = new Dialog(ViewTransactions.this);
                nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                nagDialog.setContentView(R.layout.details_popup);
                TextView name = (TextView) nagDialog.findViewById(R.id.client_name_tv);
                TextView phone = (TextView) nagDialog.findViewById(R.id.client_phone_tv);
                TextView prod = (TextView) nagDialog.findViewById(R.id.client_product_tv);
                TextView date = (TextView) nagDialog.findViewById(R.id.date_tv);
                Button call= (Button) nagDialog.findViewById(R.id.popup_call_btn);
                name.setText(transaction.getName());
                phone.setText(transaction.getPhone());
                prod.setText(transaction.getProduct());
                date.setText(transaction.getDate());
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + transaction.getPhone())));                    }
                });
               // phone.setOnClickListener();
                nagDialog.show();
            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }


        });
    }














    public class CustomAdapter extends ArrayAdapter<Transaction>
    {

         List<Transaction> filterdList;
         List<Transaction> transactions;
        public CustomAdapter(Context context, int resource, List<Transaction> objects) {
            super(context, resource, objects);
            this.transactions=objects;
            this.filterdList=objects;
        }
        class ViewHolder
        {
TextView name,product,sell_price,buy_price;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();

            try {

                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.custom_item, parent, false);
                   // holder.name = (TextView) convertView.findViewById(R.id.name_tv);
                    holder.product =(TextView) convertView.findViewById(R.id.product_tv);
                    holder.sell_price=(TextView) convertView.findViewById(R.id.sell_price_tv);
                    holder.buy_price=(TextView) convertView.findViewById(R.id.buy_price_tv);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                final Transaction transaction = transactions.get(position);
               // holder.name.setText(transaction.getName());
                holder.product.setText(transaction.getProduct());
                holder.buy_price.setText(transaction.getBuy_price());
                holder.sell_price.setText(transaction.getSell_price());
            return convertView;
            } catch (Exception e) {
                return null;
            }

        }

        @Override
        public int getCount() {return filterdList.size();}
        @Nullable
        @Override
        public Transaction getItem(int position) {return filterdList.get(position);}

        @NonNull
        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    // FilterResults filterResults = new FilterResults();
                    FilterResults results = new FilterResults();

                    //If there's nothing to filter on, return the original data for your list
                    if(charSequence == null || charSequence.length() == 0)
                    {
                        results.values = transactions;
                        results.count = transactions.size();
                    }

                    else
                    {
                        ArrayList<Transaction> filterResultsData = new ArrayList<Transaction>();
                        for(Transaction data : transactions)
                        {
                            //In this loop, you'll filter through originalData and compare each item to charSequence.
                            //If you find a match, add it to your new ArrayList
                            //I'm not sure how you're going to do comparison, so you'll need to fill out this conditional
                            if(data.getName().toLowerCase().contains(charSequence))
                            {
                                filterResultsData.add(data);
                            }
                        }
                        results.values = filterResultsData;
                        results.count = filterResultsData.size();
                    }
                    return results;
                }

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence contraint, FilterResults results) {
                    filterdList = (ArrayList<Transaction>) results.values;
                    if (results.count > 0) {
                        notifyDataSetChanged();
                    } else
                    {
                        notifyDataSetInvalidated();
                    }
                }
            };
        }
    }

}
