package edu.ncsu.dbms.wolfmedia.controllers;

import edu.ncsu.dbms.wolfmedia.models.*;
import edu.ncsu.dbms.wolfmedia.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CRUDController {

    private final CRUDService crudService;

    public CRUDController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @GetMapping("/artistHistory")
    public List<ArtistHistory> getArtistHistory() throws Exception {
        return crudService.getArtistHistory(-1);
    }

    @GetMapping("/artistHistory/{artistId}")
    public List<ArtistHistory> getArtistHistory(@PathVariable Integer artistId) throws Exception {
        return crudService.getArtistHistory(artistId);
    }

    @PostMapping("/artistHistory")
    public Boolean addArtistHistory(@RequestBody ArtistHistory artistHistory) throws Exception {
       return crudService.addArtistHistory(artistHistory);
    }

    @PutMapping("/artistHistory")
    public Boolean updateArtistHistory(@RequestBody ArtistHistory artistHistory) throws Exception {
       return crudService.updateArtistHistory(artistHistory);
    }

    @DeleteMapping("/artistHistory")
    public Boolean deleteArtistHistory(@RequestParam Integer artistId, @RequestParam Integer month,
                                       @RequestParam Integer year) throws Exception {
       return crudService.deleteArtistHistory( artistId,  month,  year);
    }

    @GetMapping("/artistType")
    public List<ArtistType> getArtistType() throws Exception {
        return crudService.getArtistType(-1);
    }

    @GetMapping("/artistType/{artistId}")
    public List<ArtistType> getArtistType(@PathVariable Integer artistId) throws Exception {
        return crudService.getArtistType(artistId);
    }

    @PostMapping("/artistType")
    public Boolean addArtistType(@RequestBody ArtistType artistType) throws Exception {
       return crudService.addArtistType(artistType);
    }

    @PutMapping("/artistType")
    public Boolean updateArtistType(@RequestBody ArtistType artistType) throws Exception {
       return crudService.updateArtistType(artistType);
    }

    @DeleteMapping("/artistType")
    public Boolean deleteArtistType(@RequestParam Integer artistId) throws Exception {
       return crudService.deleteArtistType( artistId);
    }

    @GetMapping("/belongsTo")
    public List<BelongsTo> getBelongsTo() throws Exception {
        return crudService.getBelongsTo();
    }

    @PostMapping("/belongsTo")
    public Boolean addBelongsTo(@RequestBody BelongsTo belongsTo) throws Exception {
       return crudService.addBelongsTo(belongsTo);
    }

    @DeleteMapping("/belongsTo")
    public Boolean deleteBelongsTo(@RequestParam Integer artistTypeId, @RequestParam Integer artistId) throws Exception {
       return crudService.deleteBelongsTo( artistTypeId,  artistId);
    }

    @GetMapping("/collaboratedWith")
    public List<CollaboratedWith> getCollaboratedWith() throws Exception {
        return crudService.getCollaboratedWith();
    }

    @PostMapping("/collaboratedWith")
    public Boolean addCollaboratedWith(@RequestBody CollaboratedWith collaboratedWith) throws Exception {
       return crudService.addCollaboratedWith(collaboratedWith);
    }

    @DeleteMapping("/collaboratedWith")
    public Boolean deleteCollaboratedWith(@RequestParam Integer artistId1, @RequestParam Integer artistId2) throws Exception {
       return crudService.deleteCollaboratedWith( artistId1,  artistId2);
    }

    @GetMapping("/createdBy")
    public List<CreatedBy> getCreatedBy() throws Exception {
        return crudService.getCreatedBy();
    }

    @PostMapping("/createdBy")
    public Boolean addCreatedBy(@RequestBody CreatedBy createdBy) throws Exception {
       return crudService.addCreatedBy(createdBy);
    }

    @DeleteMapping("/createdBy")
    public Boolean deleteCreatedBy(@RequestParam Integer podcastId, @RequestParam Integer hostId) throws Exception {
       return crudService.deleteCreatedBy( podcastId,  hostId);
    }

    @GetMapping("/creates")
    public List<Creates> getCreates() throws Exception {
        return crudService.getCreates();
    }

    @PostMapping("/creates")
    public Boolean addCreates(@RequestBody Creates creates) throws Exception {
       return crudService.addCreates(creates);
    }

    @DeleteMapping("/creates")
    public Boolean deleteCreates(@RequestParam Integer artistId, @RequestParam Integer songId) throws Exception {
       return crudService.deleteCreates( artistId,  songId);
    }

    @GetMapping("/distributesRoyalties")
    public List<DistributesRoyalties> getDistributesRoyalties() throws Exception {
        return crudService.getDistributesRoyalties();
    }

    @PostMapping("/distributesRoyalties")
    public Boolean addDistributesRoyalties(@RequestBody DistributesRoyalties distributesRoyalties) throws Exception {
       return crudService.addDistributesRoyalties(distributesRoyalties);
    }

    @DeleteMapping("/distributesRoyalties")
    public Boolean deleteDistributesRoyalties(@RequestParam Integer artistId, @RequestParam Integer songId,
                                              @RequestParam Integer recordId) throws Exception {
       return crudService.deleteDistributesRoyalties( artistId,  recordId, songId);
    }

    @GetMapping("/episodeFeaturesGuest")
    public List<EpisodeFeaturesGuest> getEpisodeFeaturesGuest() throws Exception {
        return crudService.getEpisodeFeaturesGuest();
    }

    @PostMapping("/episodeFeaturesGuest")
    public Boolean addEpisodeFeaturesGuest(@RequestBody EpisodeFeaturesGuest episodeFeaturesGuest) throws Exception {
       return crudService.addEpisodeFeaturesGuest(episodeFeaturesGuest);
    }

    @DeleteMapping("/episodeFeaturesGuest")
    public Boolean deleteEpisodeFeaturesGuest(@RequestParam Integer podcastId, @RequestParam Integer number,
                                              @RequestParam Integer guestId) throws Exception {
       return crudService.deleteEpisodeFeaturesGuest( podcastId, number,  guestId);
    }

    @GetMapping("/follow")
    public List<Follow> getFollow() throws Exception {
        return crudService.getFollow();
    }

    @PostMapping("/follow")
    public Boolean addFollow(@RequestBody Follow follow) throws Exception {
       return crudService.addFollow(follow);
    }

    @DeleteMapping("/follow")
    public Boolean deleteFollow(@RequestParam Integer artistId, @RequestParam Integer userId) throws Exception {
       return crudService.deleteFollow(artistId, userId);
    }

    @GetMapping("/genres")
    public List<Genres> getGenres() throws Exception {
        return crudService.getGenres();
    }

    @PostMapping("/genres")
    public Boolean addGenres(@RequestBody Genres genres) throws Exception {
       return crudService.addGenres(genres);
    }

    @DeleteMapping("/genres")
    public Boolean deleteGenres(@RequestParam Integer genreId) throws Exception {
       return crudService.deleteGenres(genreId);
    }

    @GetMapping("/givesPaymentTo")
    public List<GivesPaymentTo> getGivesPaymentTo() throws Exception {
        return crudService.getGivesPaymentTo();
    }

    @PostMapping("/givesPaymentTo")
    public Boolean addGivesPaymentTo(@RequestBody GivesPaymentTo givesPaymentTo) throws Exception {
       return crudService.addGivesPaymentTo(givesPaymentTo);
    }

    @DeleteMapping("/givesPaymentTo")
    public Boolean deleteGivesPaymentTo(@RequestParam Integer transactionId, @RequestParam Integer hostId) throws Exception {
       return crudService.deleteGivesPaymentTo(transactionId, hostId);
    }

    @GetMapping("/pays")
    public List<Pays> getPays() throws Exception {
        return crudService.getPays();
    }

    @PostMapping("/pays")
    public Boolean addPays(@RequestBody Pays pays) throws Exception {
       return crudService.addPays(pays);
    }

    @DeleteMapping("/pays")
    public Boolean deletePays(@RequestParam Integer transactionId, @RequestParam Integer userId) throws Exception {
       return crudService.deletePays(userId, transactionId);
    }

    @GetMapping("/podcastHas")
    public List<PodcastHas> getPodcastHas() throws Exception {
        return crudService.getPodcastHas();
    }

    @PostMapping("/podcastHas")
    public Boolean addPodcastHas(@RequestBody PodcastHas podcastHas) throws Exception {
       return crudService.addPodcastHas(podcastHas);
    }

    @DeleteMapping("/podcastHas")
    public Boolean deletePodcastHas(@RequestParam Integer podcastId, @RequestParam Integer genreId) throws Exception {
       return crudService.deletePodcastHas(podcastId, genreId);
    }

    @GetMapping("/podcastHistory")
    public List<PodcastHistory> getPodcastHistory() throws Exception {
        return crudService.getPodcastHistory();
    }

    @PostMapping("/podcastHistory")
    public Boolean addPodcastHistory(@RequestBody PodcastHistory podcastHistory) throws Exception {
       return crudService.addPodcastHistory(podcastHistory);
    }

    @DeleteMapping("/podcastHistory")
    public Boolean deletePodcastHistory(@RequestParam Integer podcastId, @RequestParam Integer month, @RequestParam Integer year) throws Exception {
       return crudService.deletePodcastHistory(podcastId, month, year);
    }

    @GetMapping("/receives")
    public List<Receives> getReceives() throws Exception {
        return crudService.getReceives();
    }

    @PostMapping("/receives")
    public Boolean addReceives(@RequestBody Receives receives) throws Exception {
       return crudService.addReceives(receives);
    }

    @DeleteMapping("/receives")
    public Boolean deleteReceives(@RequestParam Integer transactionId, @RequestParam Integer recordId) throws Exception {
       return crudService.deleteReceives(transactionId, recordId);
    }

    @GetMapping("/recordLabels")
    public List<RecordLabels> getRecordLabels() throws Exception {
        return crudService.getRecordLabels();
    }

    @PostMapping("/recordLabels")
    public Boolean addRecordLabels(@RequestBody RecordLabels recordLabels) throws Exception {
       return crudService.addRecordLabels(recordLabels);
    }

    @DeleteMapping("/recordLabels")
    public Boolean deleteRecordLabels(@RequestParam Integer recordId) throws Exception {
       return crudService.deleteRecordLabels(recordId);
    }

    @GetMapping("/serviceAccount")
    public List<ServiceAccount> getServiceAccount() throws Exception {
        return crudService.getServiceAccount();
    }

    @PostMapping("/serviceAccount")
    public Boolean addServiceAccount(@RequestBody ServiceAccount serviceAccount) throws Exception {
       return crudService.addServiceAccount(serviceAccount);
    }

    @DeleteMapping("/serviceAccount")
    public Boolean deleteServiceAccount(@RequestParam Integer userId) throws Exception {
       return crudService.deleteServiceAccount(userId);
    }

    @GetMapping("/songHas")
    public List<SongHas> getSongHas() throws Exception {
        return crudService.getSongHas();
    }

    @PostMapping("/songHas")
    public Boolean addSongHas(@RequestBody SongHas songHas) throws Exception {
       return crudService.addSongHas(songHas);
    }

    @DeleteMapping("/songHas")
    public Boolean deleteSongHas(@RequestParam Integer songId, @RequestParam Integer genreId) throws Exception {
       return crudService.deleteSongHas(songId, genreId);
    }

    @GetMapping("/songHistory")
    public List<SongHistory> getSongHistory() throws Exception {
        return crudService.getSongHistory();
    }

    @PostMapping("/songHistory")
    public Boolean addSongHistory(@RequestBody SongHistory songHistory) throws Exception {
       return crudService.addSongHistory(songHistory);
    }

    @DeleteMapping("/songHistory")
    public Boolean deleteSongHistory(@RequestParam Integer songId, @RequestParam Integer month, @RequestParam Integer year) throws Exception {
       return crudService.deleteSongHistory(songId, month, year);
    }

    @GetMapping("/specialGuests")
    public List<SpecialGuests> getSpecialGuests() throws Exception {
        return crudService.getSpecialGuests();
    }

    @PostMapping("/specialGuests")
    public Boolean addSpecialGuests(@RequestBody SpecialGuests specialGuests) throws Exception {
       return crudService.addSpecialGuests(specialGuests);
    }

    @DeleteMapping("/specialGuests")
    public Boolean deleteSpecialGuests(@RequestParam Integer guestId) throws Exception {
       return crudService.deleteSpecialGuests(guestId);
    }

    @GetMapping("/sponsoredBy")
    public List<SponsoredBy> getSponsoredBy() throws Exception {
        return crudService.getSponsoredBy();
    }

    @PostMapping("/sponsoredBy")
    public Boolean addSponsoredBy(@RequestBody SponsoredBy sponsoredBy) throws Exception {
       return crudService.addSponsoredBy(sponsoredBy);
    }

    @DeleteMapping("/sponsoredBy")
    public Boolean deleteSponsoredBy(@RequestParam Integer podcastId, @RequestParam Integer sponsorId) throws Exception {
       return crudService.deleteSponsoredBy(sponsorId, podcastId);
    }

    @GetMapping("/sponsors")
    public List<Sponsors> getSponsors() throws Exception {
        return crudService.getSponsors();
    }

    @PostMapping("/sponsors")
    public Boolean addSponsors(@RequestBody Sponsors sponsors) throws Exception {
       return crudService.addSponsors(sponsors);
    }

    @DeleteMapping("/sponsors")
    public Boolean deleteSponsors(@RequestParam Integer sponsorId) throws Exception {
       return crudService.deleteSponsors(sponsorId);
    }

    @GetMapping("/subscribes")
    public List<Subscribes> getSubscribes() throws Exception {
        return crudService.getSubscribes();
    }

    @PostMapping("/subscribes")
    public Boolean addSubscribes(@RequestBody Subscribes subscribes) throws Exception {
       return crudService.addSubscribes(subscribes);
    }

    @DeleteMapping("/subscribes")
    public Boolean deleteSubscribes(@RequestParam Integer userId, @RequestParam Integer podcastId) throws Exception {
       return crudService.deleteSubscribes(userId, podcastId);
    }

    @GetMapping("/trackNumbers")
    public List<TrackNumbers> getTrackNumbers() throws Exception {
        return crudService.getTrackNumbers();
    }

    @PostMapping("/trackNumbers")
    public Boolean addTrackNumbers(@RequestBody TrackNumbers trackNumbers) throws Exception {
       return crudService.addTrackNumbers(trackNumbers);
    }

    @DeleteMapping("/trackNumbers")
    public Boolean deleteTrackNumbers(@RequestParam Integer number, @RequestParam Integer albumId) throws Exception {
       return crudService.deleteTrackNumbers(albumId, number);
    }

    @GetMapping("/users")
    public List<Users> getUsers() throws Exception {
        return crudService.getUsers();
    }

    @PostMapping("/users")
    public Boolean addUsers(@RequestBody Users users) throws Exception {
       return crudService.addUsers(users);
    }

    @PutMapping("/users")
    public Boolean updateUsers(@RequestBody Users users) throws Exception {
       return crudService.updateUsers(users);
    }

    @DeleteMapping("/users")
    public Boolean deleteUsers(@RequestParam Integer userId) throws Exception {
       return crudService.deleteUsers(userId);
    }



}
