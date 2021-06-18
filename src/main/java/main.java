import model.Slot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
	public static void main(String[] args) {

		// Creates a new connection
		// .get() fetches and parses HTML file
		// Might throw IOException
		List<Slot> allSlots = new ArrayList<>();

		try {
			Document doc = Jsoup.connect("https://www.vgregion.se/ov/vaccinationstider/bokningsbara-tider/").get();

			Elements timeslots = doc.getElementsByClass("media-body");

			for (Element timeslot : timeslots) {

				Slot newSlot = new Slot();

				// Göteborg: Drive In Nötkärnan Slottskogen
				String heading = timeslot.select("h3").text();

				Element link = timeslot.select("a").first();

				// Boka tid via webben hos Drive In Nötkärnan Slottskogen
				String linkText = link.text();

				// https://formular.1177.se/etjanst/ad7ed879-138d-4cfd-ac94-83c0af422e44?externalApplication=COVID_SE2321000131-E000000016315
				String linkHref = link.attr("href");

				Element spanText = timeslot.select("span").first();

				// (Mer än 500 lediga tider kommande 2 veckor)
				String openSlots = spanText.text();

//				Senast uppdaterad: 2021-06-18 13:07
				String updated = doc.getElementsByTag("hr").next().text();

				newSlot.setHeading(heading);
				newSlot.setLinkText(linkText);
				newSlot.setLinkHref(linkHref);
				newSlot.setOpenSlots(extractOpenTimeslots(openSlots));
				newSlot.setUpdated(removeUpdatedText(updated));

				allSlots.add(newSlot);
			}

		} catch (IOException ex) {
			System.out.println("Error parsing document...");
			ex.printStackTrace();
		}
		allSlots.forEach(System.out :: println);
	}

	public static String extractOpenTimeslots(String openSlotsText) {
		// Split string to:
		// - (Mer än 500
		// - lediga tider kommande 2 veckor)"
		String[] splitAtWord = openSlotsText.split("lediga");
		// Take first part of the String, "(Mer än 500"
		// Replace everything that is not a number with "", i.e nothing
		// Returns "500"
		return splitAtWord[0].replaceAll("[^\\d]", "");
	}

	public static String removeUpdatedText(String updated) {
		String[] splitAtColon = updated.split(":");
		return splitAtColon[1] + ":" + splitAtColon[2];
	}
}
