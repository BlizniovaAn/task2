/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
window.onload = function() {
    var filterForm = document.getElementById('filter-form'),
        paginationForm = document.getElementById('pagination-form');
    filterForm.onsubmit = filterNews;
    paginationForm.onsubmit = getPieceOfNews;

    function filterNews() {
        var author = document.getElementById('author'),
            authorOption = author.value,
            pageNumber = document.getElementById('author'),
            page = document.getElementById('page'),
            body = 'command=' + encodeURIComponent('FILTER_NEWS') +
                '&authorId=' + encodeURIComponent(authorOption)+
                '&page=' + encodeURIComponent(page)+
                '&pageNumber=' + encodeURIComponent(pageNumber);
        post(body);
        return false;
    }
}