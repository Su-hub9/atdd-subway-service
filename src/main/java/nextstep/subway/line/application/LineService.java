package nextstep.subway.line.application;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.LineRepository;
import nextstep.subway.line.domain.Section;
import nextstep.subway.line.dto.LineRequest;
import nextstep.subway.line.dto.LineResponse;
import nextstep.subway.line.dto.SectionRequest;
import nextstep.subway.station.application.StationService;
import nextstep.subway.station.domain.Station;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LineService {

    private LineRepository lineRepository;
    private StationService stationService;

    public LineService(LineRepository lineRepository, StationService stationService) {
        this.lineRepository = lineRepository;
        this.stationService = stationService;
    }

    public LineResponse saveLine(LineRequest request) {
        Station upStation = stationService.findById(request.getUpStationId());
        Station downStation = stationService.findById(request.getDownStationId());
        Line persistLine = lineRepository.save(new Line(request.getName(), request.getColor(), upStation, downStation, request.getDistance()));
        return LineResponse.of(persistLine);
    }

    public List<LineResponse> findLines() {
        return LineResponse.ofList(lineRepository.findAll());
    }

    public LineResponse findLineResponseById(Long id) {
        return LineResponse.of(findById(id));
    }

    @Transactional(readOnly = true)
    public Line findById(Long id) {
        return lineRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void updateLine(Long id, LineRequest lineUpdateRequest) {
        Line persistLine = findById(id);
        persistLine.update(new Line(lineUpdateRequest.getName(), lineUpdateRequest.getColor()));
    }

    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }

    public void addLineStation(Long lineId, SectionRequest request) {
        Station upStation = stationService.findStationById(request.getUpStationId());
        Station downStation = stationService.findStationById(request.getDownStationId());
        Line persistLine = findById(lineId);
        Section section = new Section(persistLine, upStation, downStation, request.getDistance());
        persistLine.addSection(section);
    }

    public void removeLineStation(Long lineId, Long stationId) {
        Station station = stationService.findStationById(stationId);
        Line line = findById(lineId);
        line.deleteStation(station);
    }
}
