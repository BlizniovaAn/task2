/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
window.onload = function() {
    var filterForm = document.getElementById('filter-form');
    filterForm.onsubmit = filterNews;

    function filterNews() {
        var author = document.getElementById('author'),
            authorOption = author.value,
            page = document.getElementById('page').value;
            var body = 'command=' + encodeURIComponent('FILTER_NEWS') +
                '&authorId=' + encodeURIComponent(authorOption)+
                '&page=' + encodeURIComponent(page);
        post(body);
        return false;
    }
}